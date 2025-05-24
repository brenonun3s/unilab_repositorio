/*
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
*/

// Variáveis globais
let professores = [];

// Evento ao carregar o DOM
document.addEventListener('DOMContentLoaded', () => {
    verificarAutenticacao();
    carregarProfessores();
    configurarEventos();
});

// Função para verificar autenticação
function verificarAutenticacao() {
    const autenticado = localStorage.getItem('isAuthenticated') === 'true';
    const papelUsuario = localStorage.getItem('userRole');

    if (!autenticado || papelUsuario !== 'administrador') {
        if (autenticado && papelUsuario !== 'administrador') {
            sessionStorage.setItem('redirectMessage', 'Você não tem permissão para acessar a página de gerenciamento de professores.');
            sessionStorage.setItem('redirectMessageType', 'danger');
        }
        window.location.href = 'sejaBemVindo.html';
        return;
    }

    const nomeUsuario = localStorage.getItem('userName');
    const elementoNomeUsuario = document.querySelector('.user-name');
    if (nomeUsuario && elementoNomeUsuario) {
        elementoNomeUsuario.textContent = nomeUsuario;
    }
}

// Função para configurar eventos
function configurarEventos() {
    document.getElementById('filterForm').addEventListener('submit', e => {
        e.preventDefault();
        filtrarProfessores();
    });

    document.getElementById('novoProfessor').addEventListener('click', () => abrirModalProfessor());
    document.getElementById('btnNovoProfessor').addEventListener('click', () => abrirModalProfessor());
    document.getElementById('salvarProfessor').addEventListener('click', () => salvarProfessor());
    document.querySelector('button[type="reset"]').addEventListener('click', () => limparFiltros());
    document.querySelector('.logout-button').addEventListener('click', e => {
        e.preventDefault();
        logout();
    });
}

// Função para carregar professores
function carregarProfessores() {
    professores = JSON.parse(localStorage.getItem('professores')) || [];
    atualizarEstatisticas(professores);
    exibirProfessores(professores);
}

// Função para atualizar estatísticas
function atualizarEstatisticas(profs) {
    const total = profs.length;
    const ativos = profs.filter(p => p.status === 'ativo').length;
    const inativos = profs.filter(p => p.status === 'inativo').length;
    const departamentos = [...new Set(profs.map(p => p.departamento))];
    const totalDepartamentos = departamentos.length;

    document.getElementById('totalProfessores').textContent = total;
    document.getElementById('professoresAtivos').textContent = ativos;
    document.getElementById('professoresInativos').textContent = inativos;
    document.getElementById('totalDepartamentos').textContent = totalDepartamentos;
}

// Função para exibir professores na tabela
function exibirProfessores(profs) {
    const tbody = document.getElementById('professoresTable');
    tbody.innerHTML = '';

    if (profs.length === 0) {
        const tr = document.createElement('tr');
        tr.innerHTML = `<td colspan="6" class="text-center">Nenhum professor encontrado</td>`;
        tbody.appendChild(tr);
        return;
    }

    profs.forEach(prof => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${prof.nome || ''}</td>
            <td>${prof.email || ''}</td>
            <td>${prof.matricula || ''}</td>
            <td>${formatarDepartamento(prof.departamento || 'outro')}</td>
            <td><span class="status-badge status-${prof.status || 'inativo'}">${formatarStatus(prof.status || 'inativo')}</span></td>
            <td>
                <button class="btn-action" onclick="editarProfessor('${prof.id}')" title="Editar">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn-action" onclick="excluirProfessor('${prof.id}')" title="Excluir">
                    <i class="bi bi-trash"></i>
                </button>
                <button class="btn-action" onclick="mostrarSenha('${prof.id}')" title="Mostrar Senha">
                    <i class="bi bi-key"></i>
                </button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

// Função para filtrar professores
function filtrarProfessores() {
    const termoBusca = document.getElementById('searchInput').value.toLowerCase();
    const status = document.getElementById('statusFilter').value;
    const departamento = document.getElementById('departamentoFilter').value;

    const profsFiltrados = professores.filter(prof => {
        const correspondeBusca = (prof.nome || '').toLowerCase().includes(termoBusca) ||
            (prof.email || '').toLowerCase().includes(termoBusca) ||
            (prof.matricula || '').toLowerCase().includes(termoBusca);
        const correspondeStatus = !status || prof.status === status;
        const correspondeDepartamento = !departamento || prof.departamento === departamento;

        return correspondeBusca && correspondeStatus && correspondeDepartamento;
    });

    exibirProfessores(profsFiltrados);
}

// Função para limpar filtros
function limparFiltros() {
    document.getElementById('filterForm').reset();
    carregarProfessores();
}

// Função para abrir modal de professor
function abrirModalProfessor(idProf = null) {
    const modal = new bootstrap.Modal(document.getElementById('professorModal'));
    const form = document.getElementById('professorForm');
    const titulo = document.querySelector('#professorModal .modal-title');

    form.reset();
    if (idProf) {
        titulo.innerHTML = '<i class="bi bi-person-gear"></i> Editar Professor';
        const prof = obterProfessorPorId(idProf);
        if (prof) {
            form.nome.value = prof.nome || '';
            form.email.value = prof.email || '';
            form.matricula.value = prof.matricula || '';
            form.departamento.value = prof.departamento || 'computacao';
            form.status.value = prof.status || 'ativo';
            form.dataset.profId = idProf;
        }
    } else {
        titulo.innerHTML = '<i class="bi bi-person-plus"></i> Novo Professor';
        delete form.dataset.profId;
    }

    modal.show();
}

// Função para salvar professor
function salvarProfessor() {
    const form = document.getElementById('professorForm');
    const idProf = form.dataset.profId;
    const prof = {
        id: idProf || gerarId(),
        nome: form.nome.value.trim().toUpperCase(),
        email: form.email.value.trim().toLowerCase(),
        matricula: form.matricula.value.trim() || gerarMatricula(),
        departamento: form.departamento.value,
        status: form.status.value,
        senha: idProf ? obterProfessorPorId(idProf).senha : gerarSenha()
    };

    if (!validarProfessor(prof)) {
        exibirNotificacao('Por favor, preencha todos os campos obrigatórios.', 'danger');
        return;
    }

    if (idProf) {
        professores = professores.map(p => p.id === idProf ? prof : p);
    } else {
        professores.push(prof);
    }

    localStorage.setItem('professores', JSON.stringify(professores));

    try {
        const modalEl = document.getElementById('professorModal');
        const instanciaModal = bootstrap.Modal.getInstance(modalEl);
        if (instanciaModal) {
            instanciaModal.hide();
        }
    } catch (e) {
        console.error('Erro ao fechar modal:', e);
    }

    carregarProfessores();
    exibirNotificacao(`Professor ${idProf ? 'atualizado' : 'adicionado'} com sucesso!`, 'success');
}

// Função para editar professor
function editarProfessor(idProf) {
    abrirModalProfessor(idProf);
}

// Função para excluir professor
function excluirProfessor(idProf) {
    if (confirm('Tem certeza que deseja excluir este professor?')) {
        professores = professores.filter(p => p.id !== idProf);
        localStorage.setItem('professores', JSON.stringify(professores));
        carregarProfessores();
        exibirNotificacao('Professor excluído com sucesso!', 'success');
    }
}

// Função para mostrar senha do professor
function mostrarSenha(idProf) {
    const prof = obterProfessorPorId(idProf);
    if (prof) {
        alert(`Senha do professor ${prof.nome}: ${prof.senha}`);
    }
}

// Funções auxiliares
function obterProfessorPorId(idProf) {
    return professores.find(p => p.id === idProf);
}

function gerarId() {
    return Date.now().toString(36) + Math.random().toString(36).substr(2);
}

function gerarSenha() {
    const caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$!%*?&";
    let senha = "";
    for (let i = 0; i < 10; i++) {
        senha += caracteres.charAt(Math.floor(Math.random() * caracteres.length));
    }
    return senha;
}

function gerarMatricula() {
    const ano = new Date().getFullYear();
    const numero = Math.floor(Math.random() * 900000) + 100000;
    return `${ano}${numero}`;
}

function validarProfessor(prof) {
    return prof.nome && prof.email && prof.matricula && prof.departamento && prof.status;
}

function formatarStatus(status) {
    const mapaStatus = {
        'ativo': 'Ativo',
        'inativo': 'Inativo'
    };
    return mapaStatus[status] || status;
}

function formatarDepartamento(departamento) {
    const mapaDepartamentos = {
        'computacao': 'Computação',
        'matematica': 'Matemática',
        'fisica': 'Física',
        'quimica': 'Química',
        'biologia': 'Biologia',
        'engenharia': 'Engenharia',
        'letras': 'Letras',
        'outro': 'Outro'
    };
    return mapaDepartamentos[departamento] || departamento;
}

// Função para exibir notificações
function exibirNotificacao(mensagem, tipo = 'info') {
    const alerta = document.createElement('div');
    alerta.className = `alert alert-${tipo} alert-dismissible fade show`;
    alerta.role = 'alert';
    alerta.innerHTML = `
        ${mensagem}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Fechar"></button>
    `;
    const container = document.getElementById('alertContainer');
    container.appendChild(alerta);

    setTimeout(() => {
        alerta.classList.remove('show');
        alerta.classList.add('hide');
        alerta.addEventListener('transitionend', () => alerta.remove());
    }, 4000);
}

// Função de logout
function logout() {
    localStorage.setItem('isAuthenticated', 'false');
    localStorage.removeItem('userName');
    localStorage.removeItem('userRole');
    window.location.href = 'index.html';
}
