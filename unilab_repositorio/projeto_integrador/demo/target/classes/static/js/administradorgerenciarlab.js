// Autor: Rafael V. Gogge
// Copyright © 2025 Rafael V. Gogge
// Projeto: UniLab - Sistema de Gerenciamento de Laboratórios

let labModalInstance;
let laboratorios = []; // Agora apenas usado para exibição temporária

document.addEventListener('DOMContentLoaded', function () {
    checkAuth();
    setupEventListeners();
    updateStats();
    animateOnScroll();
});

function setupEventListeners() {
    document.getElementById('filterForm').addEventListener('submit', function (e) {
        e.preventDefault();
        filterLaboratories();
    });

    document.getElementById('btnNovoLaboratorio').addEventListener('click', function () {
        openLaboratoryModal();
    });

    document.getElementById('btnLimparFiltros').addEventListener('click', function () {
        clearFilters();
    });

    document.querySelector('.logout-button').addEventListener('click', function (e) {
        e.preventDefault();
        logout();
    });
}

function checkAuth() {
    const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';
    const userRole = localStorage.getItem('userRole');

    if (!isAuthenticated || userRole !== 'administrador') {
        if (isAuthenticated && userRole !== 'administrador') {
            sessionStorage.setItem('redirectMessage', 'Você não tem permissão para acessar a página de gerenciamento de laboratórios.');
            sessionStorage.setItem('redirectMessageType', 'danger');
        }
        window.location.href = 'sejaBemVindo.html';
        return;
    }

    const userName = localStorage.getItem('userName');
    const userNameElement = document.querySelector('.user-name');
    if (userName && userNameElement) {
        userNameElement.textContent = userName;
    }
}

function updateStats() {
    const total = laboratorios.length;
    const ativos = laboratorios.filter(lab => lab.status === 'ativo').length;
    const inativos = laboratorios.filter(lab => lab.status === 'inativo').length;
    const capacidadeTotal = laboratorios.reduce((acc, lab) => acc + parseInt(lab.capacidade || 0), 0);

    document.getElementById('totalLaboratorios').textContent = total;
    document.getElementById('laboratoriosAtivos').textContent = ativos;
    document.getElementById('laboratoriosInativos').textContent = inativos;
    document.getElementById('capacidadeTotal').textContent = capacidadeTotal;
}

function displayLaboratories(laboratories) {
    const tbody = document.querySelector('#laboratoriosTable tbody');
    tbody.innerHTML = '';

    if (laboratories.length === 0) {
        const tr = document.createElement('tr');
        tr.innerHTML = `<td colspan="6" class="text-center">Nenhum laboratório encontrado</td>`;
        tbody.appendChild(tr);
        return;
    }

    laboratories.forEach(lab => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${lab.nome || ''}</td>
            <td>${lab.localizacao || ''}</td>
            <td>${formatTipo(lab.tipo || 'outro')}</td>
            <td>${lab.capacidade || 0} pessoas</td>
            <td><span class="status-badge status-${lab.status || 'inativo'}">${formatStatus(lab.status || 'inativo')}</span></td>
            <td>
                <button class="btn-action" onclick="openLaboratoryModal('${lab.id}')" title="Editar">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn-action" onclick="confirmDelete('${lab.id}')" title="Excluir">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

function filterLaboratories() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const status = document.getElementById('statusFilter').value;
    const tipo = document.getElementById('tipoFilter').value;

    let filteredLabs = laboratorios.filter(lab => {
        const matchesSearch = (lab.nome || '').toLowerCase().includes(searchTerm) ||
            (lab.localizacao || '').toLowerCase().includes(searchTerm);
        const matchesStatus = !status || lab.status === status;
        const matchesTipo = !tipo || lab.tipo === tipo;
        return matchesSearch && matchesStatus && matchesTipo;
    });

    displayLaboratories(filteredLabs);
}

function clearFilters() {
    document.getElementById('filterForm').reset();
    displayLaboratories(laboratorios);
}

function adicionarFerramenta(button) {
    const input = button.parentElement.querySelector('input');
    const ferramenta = input.value.trim();

    if (ferramenta) {
        const listaFerramentas = document.getElementById('listaFerramentas');
        const ferramentaDiv = document.createElement('div');
        ferramentaDiv.className = 'ferramenta-item d-flex align-items-center mb-2';
        ferramentaDiv.innerHTML = `
            <span class="me-2">${ferramenta}</span>
            <button type="button" class="btn btn-danger btn-sm" onclick="removerFerramenta(this)">
                <i class="bi bi-trash"></i>
            </button>
        `;
        listaFerramentas.appendChild(ferramentaDiv);
        input.value = '';
        atualizarFerramentasHidden();
    }
}

function removerFerramenta(button) {
    button.parentElement.remove();
    atualizarFerramentasHidden();
}

function atualizarFerramentasHidden() {
    const ferramentas = [];
    document.querySelectorAll('.ferramenta-item span').forEach(span => {
        ferramentas.push(span.textContent);
    });
    document.getElementById('ferramentas').value = JSON.stringify(ferramentas);
}

function openLaboratoryModal(labId = null) {
    const modal = new bootstrap.Modal(document.getElementById('laboratorioModal'));
    const form = document.getElementById('laboratorioForm');
    const title = document.getElementById('modalTitle');
    const listaFerramentas = document.getElementById('listaFerramentas');

    form.reset();
    listaFerramentas.innerHTML = '';

    if (labId) {
        title.textContent = 'Editar Laboratório';
        const lab = laboratorios.find(l => l.id === labId);
        if (lab) {
            form.nome.value = lab.nome || '';
            form.localizacao.value = lab.localizacao || '';
            form.tipo.value = lab.tipo || 'outro';
            form.capacidade.value = lab.capacidade || 0;
            form.status.value = lab.status || 'inativo';
            form.dataset.labId = labId;

            if (lab.ferramentas && Array.isArray(lab.ferramentas)) {
                lab.ferramentas.forEach(ferramenta => {
                    const ferramentaDiv = document.createElement('div');
                    ferramentaDiv.className = 'ferramenta-item d-flex align-items-center mb-2';
                    ferramentaDiv.innerHTML = `
                        <span class="me-2">${ferramenta}</span>
                        <button type="button" class="btn btn-danger btn-sm" onclick="removerFerramenta(this)">
                            <i class="bi bi-trash"></i>
                        </button>
                    `;
                    listaFerramentas.appendChild(ferramentaDiv);
                });
                document.getElementById('ferramentas').value = JSON.stringify(lab.ferramentas);
            }
        }
    } else {
        title.textContent = 'Novo Laboratório';
        delete form.dataset.labId;
    }

    modal.show();
}

function confirmDelete(labId) {
    if (confirm('Tem certeza que deseja excluir este laboratório?')) {
        // Aqui você pode chamar uma função Java via fetch/AJAX
        showNotification('Ação enviada para o backend.', 'info');
    }
}

function formatStatus(status) {
    return { 'ativo': 'Ativo', 'inativo': 'Inativo' }[status] || status;
}

function formatTipo(tipo) {
    return {
        'computacao': 'Computação',
        'fisica': 'Física',
        'quimica': 'Química',
        'biologia': 'Biologia',
        'outro': 'Outro'
    }[tipo] || tipo;
}

function showNotification(message, type = 'info') {
    const notification = document.getElementById('notification');
    const notificationBody = document.getElementById('notificationBody');

    if (!notification || !notificationBody) {
        alert(message);
        return;
    }

    notificationBody.textContent = message;
    notification.className = `toast align-items-center text-white bg-${type} border-0`;

    try {
        const toast = new bootstrap.Toast(notification);
        toast.show();
    } catch (e) {
        alert(message);
    }
}

function logout() {
    localStorage.clear();
    window.location.href = 'index.html';
}

function animateOnScroll() {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('animate-in');
            }
        });
    }, { threshold: 0.1 });

    document.querySelectorAll('.card, .stat-card, .ferramenta-item').forEach((el, index) => {
        el.style.opacity = '0';
        el.style.animationDelay = `${index * 0.1}s`;
        observer.observe(el);
    });
}
