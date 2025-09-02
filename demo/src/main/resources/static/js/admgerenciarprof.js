/* 
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
*/

// Configurar event listeners
function setupEventListeners() {
    // Formulário de filtros
    document.getElementById('filterForm').addEventListener('submit', function(e) {
        e.preventDefault();
        filterProfessores();
    });



    // Botão de limpar filtros
    document.querySelector('button[type="reset"]').addEventListener('click', function() {
        clearFilters();
    });
    
    // Botão de logout
    document.querySelector('.logout-button').addEventListener('click', function(e) {
        e.preventDefault();
        logout();
    });
}



// Filtrar professores
function filterProfessores() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const status = document.getElementById('statusFilter').value;
    const departamento = document.getElementById('departamentoFilter').value;

    let filteredProfs = professores.filter(prof => {
        const matchesSearch = (prof.nome || '').toLowerCase().includes(searchTerm) ||
                            (prof.email || '').toLowerCase().includes(searchTerm) ||
                            (prof.matricula || '').toLowerCase().includes(searchTerm);
        const matchesStatus = !status || prof.status === status;
        const matchesDepartamento = !departamento || prof.departamento === departamento;
        
        return matchesSearch && matchesStatus && matchesDepartamento;
    });

}

// Limpar filtros
function clearFilters() {
    document.getElementById('filterForm').reset();

}



// Sistema de notificações
function showNotification(message, type = 'info') {
    // Verificar se existe um elemento de notificação no DOM
    let notification = document.getElementById('notification');
    
    // Se não existir, criar um
    if (!notification) {
        notification = document.createElement('div');
        notification.id = 'notification';
        notification.className = `toast align-items-center text-white bg-${type} border-0`;
        notification.setAttribute('role', 'alert');
        notification.setAttribute('aria-live', 'assertive');
        notification.setAttribute('aria-atomic', 'true');
        notification.style.position = 'fixed';
        notification.style.bottom = '20px';
        notification.style.right = '20px';
        notification.style.zIndex = '5000';
        
        notification.innerHTML = `
            <div class="d-flex">
                <div class="toast-body" id="notificationBody">
                    ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Fechar"></button>
            </div>
        `;
        
        document.body.appendChild(notification);
    } else {
        const notificationBody = document.getElementById('notificationBody');
        if (notificationBody) {
            notificationBody.textContent = message;
        }
        notification.className = `toast align-items-center text-white bg-${type} border-0`;
    }
    
    try {
        const toast = new bootstrap.Toast(notification);
        toast.show();
    } catch (e) {
        console.error('Erro ao mostrar notificação:', e);
        alert(message);
    }
}

// Logout
function logout() {
    localStorage.removeItem('isAuthenticated');
    localStorage.removeItem('userRole');
    localStorage.removeItem('userName');
    localStorage.removeItem('rememberMe');
    localStorage.removeItem('username');
    window.location.href = 'index.html';
}