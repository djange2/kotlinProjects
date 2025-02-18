import kotlin.math.pow

fun corBanda(cor: String): Int{
    val tabelaCor1 = mapOf(
        "Preto" to 0,
        "Marrom" to 1,
        "Vermelho" to 2,
        "Laranja" to 3,
        "Amarelo" to 4,
        "Verde" to 5,
        "Azul" to 6,
        "Violeta" to 7,
        "Cinza" to 8,
        "Branco" to 9
    )
    return (tabelaCor1[cor] ?: -1)
}

fun tolerancia(cor: String): Double{
    val toleranciaCores = mapOf(
        "Prata" to 0.1,
        "Ouro" to 0.05,
        "Marrom" to 0.01,
        "Vermelho" to 0.02,
        "Verde" to 0.005,
        "Azul" to 0.0025,
        "Violeta" to 0.001
    )
    return (toleranciaCores[cor] ?: 0.0) * 100
}

fun main() {
    print("Quantas bandas tem o resistor (3, 4 ou 5)? ")
    val numCores = readln().toIntOrNull() ?: return println("Número inválido.")

    print("Digite a primeira cor: ")
    val cor1 = corBanda(readln().replaceFirstChar { it.uppercase() })

    print("Digite a segunda cor: ")
    val cor2 = corBanda(readln().replaceFirstChar { it.uppercase() })

    if (cor1 == -1 || cor2 == -1) {
        println("Cor inválida.")
        return
    }

    var cor3 = -1
    if (numCores > 3) {
        print("Digite a terceira cor: ")
        cor3 = corBanda(readln().replaceFirstChar { it.uppercase() })
        if (cor3 == -1) {
            println("Cor inválida.")
            return
        }
    }

    print("Digite a cor do multiplicador: ")
    val multiplicador = corBanda(readln().replaceFirstChar { it.uppercase() })
    if (multiplicador == -1) {
        println("Cor inválida.")
        return
    }

    var tol = 0.0
    if (numCores == 4 || numCores == 5) {
        print("Digite a cor da tolerância: ")
        tol = tolerancia(readln().replaceFirstChar { it.uppercase() })
    }

    val resistencia = if (numCores == 3) {
        (cor1 * 10 + cor2) * 10.0.pow(multiplicador)
    } else {
        (cor1 * 100 + cor2 * 10 + cor3) * 10.0.pow(multiplicador)
    }

    val resultado = if (tol > 0) {
        "Resistência: ${resistencia}Ω com tolerância de $tol%"
    } else {
        "Resistência: ${resistencia}Ω"
    }

    println(resultado)
}