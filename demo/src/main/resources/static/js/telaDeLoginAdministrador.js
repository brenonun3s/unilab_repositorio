/*
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
    Enhanced by v0
*/

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm")
    const loginBtn = document.querySelector(".btn-login")
    const usuarioInput = document.getElementById("username")
    const feedback = document.getElementById("feedback")

    function showFeedback(message, type = "success") {
        feedback.className = `alert alert-${type} alert-dismissible fade show`
        feedback.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        `
        feedback.style.display = "block"
        feedback.style.animation = "none"
        feedback.offsetHeight
        feedback.style.animation = "alertAppear 0.5s ease-out"

        setTimeout(() => {
            feedback.style.animation = "fadeOut 0.5s ease-in forwards"
            setTimeout(() => {
                feedback.style.display = "none"
            }, 500)
        }, 4000)
    }

    function setButtonLoading(button, isLoading) {
        const btnText = button.querySelector(".btn-text")
        const btnLoader = button.querySelector(".btn-loader")

        if (isLoading) {
            btnText.classList.add("d-none")
            btnLoader.classList.remove("d-none")
            button.disabled = true
        } else {
            btnText.classList.remove("d-none")
            btnLoader.classList.add("d-none")
            button.disabled = false
        }
    }

    document.addEventListener("DOMContentLoaded", () => {
        // Captura query param de erro do backend
        const params = new URLSearchParams(window.location.search);
        if (params.has("error")) {
            showFeedback("Usuário ou senha incorretos", "danger");
        }

        // Apenas bloqueia o botão no submit
        loginForm.addEventListener("submit", () => {
            setButtonLoading(loginBtn, true);
        });
    });


    // Keyframes dinâmicos (caso ainda não existam)
    if (!document.querySelector("#extraKeyframes")) {
        const style = document.createElement("style")
        style.id = "extraKeyframes"
        style.textContent = `
            @keyframes alertAppear {
                0% { opacity: 0; transform: translateY(-10px); }
                100% { opacity: 1; transform: translateY(0); }
            }
            @keyframes fadeOut {
                from { opacity: 1; }
                to { opacity: 0; transform: translateY(-10px); }
            }
            @keyframes cardExit {
                0% { transform: translateY(0) scale(1); opacity: 1; }
                100% { transform: translateY(-30px) scale(0.9); opacity: 0; }
            }
        `
        document.head.appendChild(style)
    }
})
