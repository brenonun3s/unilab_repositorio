/* 
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
*/

document.addEventListener('DOMContentLoaded', () => {
    const chatIcon = document.querySelector('.chat-icon');
    const chatPanel = document.querySelector('.chat-panel');
    const chatClose = document.querySelector('.chat-close');

    if (!chatIcon || !chatPanel || !chatClose) {
        console.error("Elementos do assistente não encontrados!");
        return;
    }

    chatIcon.addEventListener('click', () => {
        chatPanel.classList.toggle('open');
    });

    chatClose.addEventListener('click', () => {
        chatPanel.classList.remove('open');
    });

    inicializarAssistente();

});

// Função para inicializar o assistente
function inicializarAssistente() {
    // Configura o timer de inatividade
    let tempoInativo = 0;
    const tempoLimite = 20; // 20 segundos

    // Função para resetar o timer
    function resetarTimer() {
        tempoInativo = 0;
    }

    // Função para verificar inatividade
    function verificarInatividade() {
        tempoInativo++;
        if (tempoInativo >= tempoLimite) {
            mostrarMensagem('Notei que você está há algum tempo na página. Posso ajudar em algo?', 'assistente');
            resetarTimer();
        }
    }

    // Adiciona eventos para detectar atividade do usuário
    ['mousemove', 'keypress', 'click', 'scroll'].forEach(evento => {
        document.addEventListener(evento, resetarTimer);
    });

    // Inicia o timer de verificação
    setInterval(verificarInatividade, 1000);
} 