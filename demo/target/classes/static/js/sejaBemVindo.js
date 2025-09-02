/* 
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
    Refatorado com animações avançadas
*/

// Função para abrir o modal de configurações
function openConfigModal() {
    const configModal = new bootstrap.Modal(document.getElementById('configModal'));
    configModal.show();
}

// Função para animar contadores
function animateCounters() {
    const counters = document.querySelectorAll('.counter');

    counters.forEach(counter => {
        const target = parseInt(counter.textContent);
        const duration = 2000; // 2 segundos
        const step = target / (duration / 30);
        let current = 0;

        const updateCounter = () => {
            if (current < target) {
                current += step;
                counter.textContent = Math.ceil(current);
                if (current >= target) {
                    counter.textContent = target;
                    clearInterval(interval);
                }
            }
        };

        counter.textContent = '0';
        const interval = setInterval(updateCounter, 30);
    });
}

// Função para mostrar feedback com animação
function showFeedback(message, type = 'success') {
    const feedback = document.createElement('div');
    feedback.className = `alert alert-${type} alert-dismissible fade show position-fixed top-0 end-0 m-3`;
    feedback.style.zIndex = '9999';
    feedback.style.opacity = '0';
    feedback.style.transform = 'translateY(-20px)';
    feedback.style.transition = 'opacity 0.3s ease, transform 0.3s ease';

    feedback.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;
    document.body.appendChild(feedback);

    setTimeout(() => {
        feedback.style.opacity = '1';
        feedback.style.transform = 'translateY(0)';
    }, 10);

    setTimeout(() => {
        feedback.style.opacity = '0';
        feedback.style.transform = 'translateY(-20px)';
        setTimeout(() => {
            feedback.remove();
        }, 300);
    }, 5000);
}

// Função para simular notificações
function simulateNotifications() {
    const notificationList = document.querySelector('.notification-list');
    const notifications = [
        {
            icon: 'bi-calendar-check',
            title: 'Novo Agendamento',
            description: 'Um novo agendamento foi realizado para o Laboratório de Informática.',
            time: '5 minutos atrás'
        },
        {
            icon: 'bi-exclamation-triangle',
            title: 'Manutenção Programada',
            description: 'O Laboratório de Química passará por manutenção amanhã.',
            time: '1 hora atrás'
        },
        {
            icon: 'bi-person-plus',
            title: 'Novo Professor',
            description: 'Um novo professor foi cadastrado no sistema.',
            time: '2 horas atrás'
        }
    ];

    notificationList.innerHTML = '';

    notifications.forEach((notification, index) => {
        const notificationItem = document.createElement('div');
        notificationItem.className = 'notification-item';
        notificationItem.style.opacity = '0';
        notificationItem.style.transform = 'translateY(20px)';
        notificationItem.style.transition = 'opacity 0.3s ease, transform 0.3s ease';
        notificationItem.style.transitionDelay = `${index * 0.1}s`;

        notificationItem.innerHTML = `
            <div class="notification-icon">
                <i class="bi ${notification.icon}"></i>
            </div>
            <div class="notification-content">
                <h6>${notification.title}</h6>
                <p>${notification.description}</p>
                <small>${notification.time}</small>
            </div>
        `;

        notificationList.appendChild(notificationItem);

        setTimeout(() => {
            notificationItem.style.opacity = '1';
            notificationItem.style.transform = 'translateY(0)';
        }, 10);
    });
}

// Quando o DOM estiver carregado
document.addEventListener('DOMContentLoaded', function () {
    setTimeout(() => {
        document.body.classList.add('loaded', 'opacity-100');
    }, 100);

    const actionCards = document.querySelectorAll('.action-card');
    const fadeElements = document.querySelectorAll('.fade-up-element, .slide-in-left, .slide-in-right, .zoom-in-element');
    const notificationModal = document.getElementById('notificationModal');
    const userDropdown = document.getElementById('userDropdown');
    const userNameElement = document.querySelector('.user-name');
    const logoutButton = document.querySelector('.logout-button');
    const notificationList = document.querySelector('.notification-list');
    const navbar = document.getElementById('navbar');

    // Verificar e exibir mensagens de redirecionamento
    const redirectMessage = sessionStorage.getItem('redirectMessage');
    const redirectMessageType = sessionStorage.getItem('redirectMessageType') || 'info';

    if (redirectMessage) {
        showFeedback(redirectMessage, redirectMessageType);
        sessionStorage.removeItem('redirectMessage');
        sessionStorage.removeItem('redirectMessageType');
    }

    // Adicionar event listeners para os cards de ação
    actionCards.forEach(card => {
        card.addEventListener('click', function () {
            const action = this.getAttribute('data-action');
            this.style.transform = 'scale(0.95)';
            setTimeout(() => {
                this.style.transform = '';
            }, 150);
        });

        card.addEventListener('keydown', function (e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                this.click();
            }
        });
    });


    // Event Listener para logout
    logoutButton.addEventListener('click', function (e) {
        e.preventDefault();

        this.style.transform = 'scale(0.95)';
        setTimeout(() => {
            this.style.transform = '';
        }, 150);

        showFeedback('Logout realizado com sucesso. Redirecionando...', 'info');

        setTimeout(() => {
            localStorage.clear();
            window.location.href = 'index.html';
        }, 2000);
    });

    // Adicionar animações scroll-triggered usando IntersectionObserver
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const delay = entry.target.getAttribute('data-delay') || 0;

                setTimeout(() => {
                    entry.target.classList.add('visible');

                    if (entry.target.classList.contains('stats-section')) {
                        animateCounters();
                    }
                }, delay);

                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    fadeElements.forEach(element => observer.observe(element));
    actionCards.forEach(card => observer.observe(card));
    const statsSection = document.querySelector('.stats-section');
    if (statsSection) observer.observe(statsSection);

    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            navbar.classList.add('scrolled');
        } else {
            navbar.classList.remove('scrolled');
        }
    });

    document.querySelectorAll('[role="button"]').forEach(button => {
        button.addEventListener('keydown', function (event) {
            if (event.key === 'Enter' || event.key === ' ') {
                event.preventDefault();
                button.click();
            }
        });
    });
});
