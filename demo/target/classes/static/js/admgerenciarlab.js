/* 
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
*/


// Event Listeners
function setupEventListeners() {
    // Formulário de filtros
    document.getElementById('filterForm').addEventListener('submit', function(e) {
        e.preventDefault();
        filterLaboratories();
    });

    // Botão de limpar filtros
    document.getElementById('btnLimparFiltros').addEventListener('click', function() {
        clearFilters();
    });
    
    // Botão de logout
    document.querySelector('.logout-button').addEventListener('click', function(e) {
        e.preventDefault();
        logout();
    });
}



// Filtrar laboratórios
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


}

// Limpar filtros
function clearFilters() {
    document.getElementById('filterForm').reset();

}



// Sistema de notificações
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

// Função para animar elementos quando aparecem na tela
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

// Função para adicionar efeito de ripple nos botões
function addRippleEffect(event) {
    const button = event.currentTarget;
    const ripple = document.createElement('span');
    const rect = button.getBoundingClientRect();
    
    ripple.className = 'ripple';
    ripple.style.left = `${event.clientX - rect.left}px`;
    ripple.style.top = `${event.clientY - rect.top}px`;
    
    button.appendChild(ripple);
    
    ripple.addEventListener('animationend', () => {
        ripple.remove();
    });
}


// Função para mostrar notificações
function showNotification(title, message) {
    const toast = document.createElement('div');
    toast.className = 'notification-toast';
    toast.innerHTML = `
        <div class="notification-header">
            <strong>${title}</strong>
            <button type="button" class="btn-close"></button>
        </div>
        <div class="notification-body">
            ${message}
        </div>
    `;
    
    document.body.appendChild(toast);
    
    requestAnimationFrame(() => {
        toast.style.transform = 'translateX(0)';
        toast.style.opacity = '1';
    });
    
    setTimeout(() => {
        toast.style.transform = 'translateX(100%)';
        toast.style.opacity = '0';
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    animateOnScroll();
    
    // Adiciona efeito de ripple aos botões
    document.querySelectorAll('.btn').forEach(button => {
        button.addEventListener('click', addRippleEffect);
    });
    
    // Adiciona animação ao hover nas linhas da tabela
    document.querySelectorAll('tbody tr').forEach(row => {
        row.addEventListener('mouseenter', () => {
            row.style.transform = 'scale(1.01)';
            row.style.backgroundColor = 'var(--table-row-hover)';
        });
        
        row.addEventListener('mouseleave', () => {
            row.style.transform = 'scale(1)';
            row.style.backgroundColor = '';
        });
    });
});
