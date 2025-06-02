/* 
    Autor: Rafael V. Gogge
    Copyright © 2025 Rafael V. Gogge
    Projeto: UniLab - Sistema de Gerenciamento de Laboratórios
    Enhanced by v0
*/
document.addEventListener("DOMContentLoaded", () => {
    // Seleção de elementos do DOM
    const loginForm = document.getElementById("loginForm")
    const rememberMeCheckbox = document.getElementById("rememberMe")
    const passwordToggle = document.querySelector(".password-toggle")
    const feedback = document.getElementById("feedback")
    const recoveryForm = document.getElementById("recoveryForm")
    const recoveryEmail = document.getElementById("recoveryEmail")
    const sendRecoveryBtn = document.getElementById("sendRecovery")
    const loginBtn = document.querySelector(".btn-login")

    // Estado do formulário
    let isSubmitting = false

    // Função para mostrar feedback
    function showFeedback(message, type = "success") {
        feedback.className = `alert alert-${type} alert-dismissible fade show`
        feedback.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        `
        feedback.style.display = "block"

        // Adicionar animação de entrada
        feedback.style.animation = "none"
        feedback.offsetHeight
        feedback.style.animation = "alertAppear 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94)"

        // Remover alerta após 5 segundos
        setTimeout(() => {
            feedback.style.animation = "fadeOut 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94) forwards"
            setTimeout(() => {
                feedback.style.display = "none"
            }, 500)
        }, 5000)
    }

    // Função para animar elemento com efeito de shake
    function animateShake(element) {
        element.style.animation = "none"
        element.offsetHeight
        element.style.animation = "shake 0.5s cubic-bezier(.36,.07,.19,.97) both"
        element.addEventListener("animationend", () => {
            element.style.animation = ""
        }, { once: true })
    }

    // Keyframes de shake e fadeOut
    if (!document.querySelector("#shakeKeyframes")) {
        const style = document.createElement("style")
        style.id = "shakeKeyframes"
        style.textContent = `
            @keyframes shake {
                10%, 90% { transform: translate3d(-1px, 0, 0); }
                20%, 80% { transform: translate3d(2px, 0, 0); }
                30%, 50%, 70% { transform: translate3d(-3px, 0, 0); }
                40%, 60% { transform: translate3d(3px, 0, 0); }
            }
            @keyframes fadeOut {
                from { opacity: 1; transform: translateY(0); }
                to { opacity: 0; transform: translateY(-10px); }
            }
        `
        document.head.appendChild(style)
    }

    // Mostrar estado de loading no botão
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

    // Efeito de foco nos inputs
    document.querySelectorAll(".form-control").forEach((input) => {
        input.addEventListener("focus", () => {
            input.parentElement.classList.add("input-focus")
        })

        input.addEventListener("blur", () => {
            input.parentElement.classList.remove("input-focus")
        })
    })

    // Simula login bem-sucedido
    setButtonLoading(loginBtn, true)
    showFeedback("Login realizado com sucesso!", "success")

    document.querySelector(".login-card").style.animation =
        "cardExit 0.8s cubic-bezier(0.25, 0.46, 0.45, 0.94) forwards"

    if (!document.querySelector("#exitKeyframes")) {
        const style = document.createElement("style")
        style.id = "exitKeyframes"
        style.textContent = `
            @keyframes cardExit {
                0% { transform: translateY(0) scale(1); opacity: 1; }
                100% { transform: translateY(-30px) scale(0.9); opacity: 0; }
            }
        `
        document.head.appendChild(style)
    }

    setTimeout(() => {
        window.location.href = "seja-bem-vindo"
    }, 1500)

    // Recuperação de senha
    sendRecoveryBtn.addEventListener("click", async () => {
        if (!recoveryEmail.value.trim()) {
            recoveryEmail.classList.add("is-invalid")
            animateShake(recoveryEmail.parentElement)
            return
        }

        recoveryEmail.classList.remove("is-invalid")
        recoveryEmail.classList.add("is-valid")

        setButtonLoading(sendRecoveryBtn, true)

        setTimeout(() => {
            showFeedback("E-mail de recuperação enviado com sucesso!", "success")

            const modalContent = document.querySelector(".modal-content")
            modalContent.style.animation = "modalExit 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94) forwards"

            if (!document.querySelector("#modalExitKeyframes")) {
                const style = document.createElement("style")
                style.id = "modalExitKeyframes"
                style.textContent = `
                    @keyframes modalExit {
                        0% { transform: scale(1); opacity: 1; }
                        100% { transform: scale(0.9); opacity: 0; }
                    }
                `
                document.head.appendChild(style)
            }

            setTimeout(() => {
                const modalElement = document.getElementById("recoveryModal")
                const modal = bootstrap.Modal.getInstance(modalElement)
                modal.hide()
                recoveryForm.reset()

                setButtonLoading(sendRecoveryBtn, false)

                setTimeout(() => {
                    modalContent.style.animation = ""
                }, 300)
            }, 500)
        }, 1500)
    })

    // Efeitos de hover nos botões
    document.querySelectorAll(".btn").forEach((btn) => {
        btn.addEventListener("mouseenter", () => {
            btn.style.transform = "translateY(-3px)"
            btn.style.boxShadow = "0 5px 15px rgba(13, 110, 253, 0.4)"
        })

        btn.addEventListener("mouseleave", () => {
            btn.style.transform = ""
            btn.style.boxShadow = ""
        })
    })

    // Partículas flutuantes
    function createParticles() {
        const particles = document.createElement("div")
        particles.className = "particles"
        document.body.appendChild(particles)

        for (let i = 0; i < 50; i++) {
            const particle = document.createElement("div")
            particle.className = "particle"
            particle.style.left = `${Math.random() * 100}%`
            particle.style.top = `${Math.random() * 100}%`
            particle.style.animationDelay = `${Math.random() * 5}s`
            particle.style.animationDuration = `${5 + Math.random() * 10}s`
            particle.style.opacity = Math.random() * 0.5
            particle.style.width = particle.style.height = `${Math.random() * 10 + 1}px`
            particles.appendChild(particle)
        }

        if (!document.querySelector("#particleStyles")) {
            const style = document.createElement("style")
            style.id = "particleStyles"
            style.textContent = `
                .particles {
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    z-index: -1;
                    overflow: hidden;
                }
                .particle {
                    position: absolute;
                    background: rgba(255, 255, 255, 0.5);
                    border-radius: 50%;
                    pointer-events: none;
                    animation: float 15s infinite linear;
                }
                @keyframes float {
                    0% { transform: translateY(0) translateX(0) rotate(0); }
                    100% { transform: translateY(-100vh) translateX(100px) rotate(360deg); }
                }
            `
            document.head.appendChild(style)
        }
    }

    createParticles()

    const bootstrap = window.bootstrap
})
