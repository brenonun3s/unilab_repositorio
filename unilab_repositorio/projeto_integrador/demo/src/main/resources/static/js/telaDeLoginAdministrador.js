/*
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
    Enhanced by v0
*/

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm")
    const loginBtn = document.querySelector(".btn-login")
    const usuarioInput = document.getElementById("username") // Corrigido para "username"
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

    loginForm.addEventListener("submit", (e) => {
        e.preventDefault()
        const usuario = usuarioInput.value.trim().toLowerCase()
        setButtonLoading(loginBtn, true)

        let destino = ""

        if (usuario.includes("admin")) {
            destino = "/main/seja-bem-vindo-adm"
        } else {
            destino = "/main/seja-bem-vindo-professor"
        }

        showFeedback("Login realizado com sucesso!", "success")

        document.querySelector(".login-card").style.animation =
            "cardExit 0.8s cubic-bezier(0.25, 0.46, 0.45, 0.94) forwards"

        setTimeout(() => {
            window.location.href = destino
        }, 1500)
    })

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
