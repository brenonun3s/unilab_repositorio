/* 
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
*/

// Elementos do DOM
const filterForm = document.getElementById('filterForm');
const agendamentosTable = document.getElementById('agendamentosTable');
const notificationModal = document.getElementById('notificationModal');
const userDropdown = document.getElementById('userDropdown');
const logoutButton = document.querySelector('.logout-button');
const detalhesModal = document.getElementById('detalhesModal');

// Variáveis esperadas (certifique-se que estão definidas em outro lugar do seu script ou backend)
const userName = localStorage.getItem('userName'); // exemplo
const userRole = localStorage.getItem('userRole'); // exemplo

// Event Listeners
filterForm.addEventListener('submit', handleFilter);
logoutButton.addEventListener('click', handleLogout);

// Atualizar nome do usuário
document.querySelector('.user-name').textContent = userName || 'Usuário';

// Ajustar título da página baseado no tipo de usuário
const pageTitle = document.querySelector('.page-header h1');
if (userRole === 'professor') {
    pageTitle.textContent = 'Meus Agendamentos';
} else {
    pageTitle.textContent = 'Histórico de Agendamentos';

    // Limpar tabela
    agendamentosTable.innerHTML = '';
}

// =================== FUNÇÕES ===================

// Handler para logout
function handleLogout() {
    localStorage.clear();
    window.location.href = 'index.html';
}

// Função para mostrar feedback
function showFeedback(message, type = 'success') {
    const feedback = document.createElement('div');
    feedback.className = `alert alert-${type} alert-dismissible fade show position-fixed top-0 end-0 m-3`;
    feedback.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    document.body.appendChild(feedback);

    setTimeout(() => {
        feedback.remove();
    }, 5000);
}

// Função para simular notificações
function simulateNotifications() {
    const notificationList = document.querySelector('.notification-list');
    const notifications = [
        {
            type: 'info',
            title: 'Novo Agendamento',
            message: 'Um novo agendamento foi realizado para o Laboratório de Informática 1',
            time: '5 minutos atrás'
        },
        {
            type: 'warning',
            title: 'Agendamento Pendente',
            message: 'Você tem um agendamento pendente de confirmação',
            time: '1 hora atrás'
        },
        {
            type: 'success',
            title: 'Agendamento Confirmado',
            message: 'Seu agendamento foi confirmado com sucesso',
            time: '2 horas atrás'
        }
    ];

    notifications.forEach(notification => {
        const notificationItem = document.createElement('div');
        notificationItem.className = 'notification-item';
        notificationItem.innerHTML = `
            <div class="notification-icon">
                <i class="bi bi-${getNotificationIcon(notification.type)}"></i>
            </div>
            <div class="notification-content">
                <h6>${notification.title}</h6>
                <p>${notification.message}</p>
                <small>${notification.time}</small>
            </div>
        `;
        notificationList.appendChild(notificationItem);
    });
}

