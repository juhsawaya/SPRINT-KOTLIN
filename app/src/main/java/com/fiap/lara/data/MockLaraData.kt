package com.fiap.lara.data

import com.fiap.lara.model.JourneyStage
import com.fiap.lara.model.LaraMessage
import com.fiap.lara.model.MilkBank
import com.fiap.lara.model.NutrizForm
import com.fiap.lara.model.NutrizJourney
import com.fiap.lara.model.Sentiment

object MockLaraData {
    val journeys = listOf(
        NutrizJourney(
            id = 1,
            name = "Camila Santos",
            city = "Sao Paulo",
            homeAddress = "Vila Mariana, Sao Paulo",
            babyProfile = "Primeiro bebe, parto normal, mae com boa rede de apoio.",
            postpartumDay = 12,
            currentStage = JourneyStage.Invitation,
            readinessScore = 78,
            sentiment = Sentiment.Ready,
            nearestMilkBank = MilkBank(
                name = "Banco de Leite Humano do Hospital Sao Paulo",
                distanceKm = 2.4,
                phone = "(11) 5576-4321",
                nextAvailableSlot = "Amanha, 10h30"
            ),
            emotionalMemory = listOf(
                "Disse que tem medo de sentir dor na primeira ordenha.",
                "Mostrou interesse porque o bebe da amiga precisou de leite humano.",
                "Prefere mensagens curtas no periodo da manha."
            ),
            messages = listOf(
                LaraMessage(2, "Acolhimento", "Oi, Camila. Como voce esta se sentindo hoje?"),
                LaraMessage(6, "Educacao", "Seu corpo ainda esta se adaptando. Posso te explicar como a doacao funciona sem pressa."),
                LaraMessage(12, "Convite", "Existe um BLH perto da sua casa com horario amanha. Quer ver as opcoes?")
            ),
            needsHumanSupport = false
        ),
        NutrizJourney(
            id = 2,
            name = "Renata Lima",
            city = "Santo Andre",
            homeAddress = "Bairro Jardim, Santo Andre",
            babyProfile = "Bebe prematuro, mae exausta e com rotina intensa de consultas.",
            postpartumDay = 7,
            currentStage = JourneyStage.Education,
            readinessScore = 34,
            sentiment = Sentiment.Tired,
            nearestMilkBank = MilkBank(
                name = "BLH Hospital Estadual Mario Covas",
                distanceKm = 4.8,
                phone = "(11) 4993-5454",
                nextAvailableSlot = "Sexta-feira, 14h"
            ),
            emotionalMemory = listOf(
                "Relatou cansaco e inseguranca com a pega do bebe.",
                "Responde melhor quando a LARA valida o esforco dela antes de orientar.",
                "Ainda nao recebeu convite direto para doacao."
            ),
            messages = listOf(
                LaraMessage(2, "Acolhimento", "Renata, voce passou por muita coisa nos ultimos dias. Estou aqui para te acompanhar."),
                LaraMessage(5, "Educacao", "Muitas maes de prematuros sentem inseguranca. Podemos ir um passo por vez.")
            ),
            needsHumanSupport = false
        ),
        NutrizJourney(
            id = 3,
            name = "Aline Rocha",
            city = "Osasco",
            homeAddress = "Centro, Osasco",
            babyProfile = "Segundo bebe, boa producao de leite, demonstrou vontade de ajudar.",
            postpartumDay = 18,
            currentStage = JourneyStage.Conversion,
            readinessScore = 91,
            sentiment = Sentiment.Secure,
            nearestMilkBank = MilkBank(
                name = "Banco de Leite Humano de Osasco",
                distanceKm = 1.3,
                phone = "(11) 3681-2220",
                nextAvailableSlot = "Hoje, 16h"
            ),
            emotionalMemory = listOf(
                "Ja doou na primeira gestacao e quer retomar.",
                "Pediu orientacao sobre retirada e armazenamento seguro.",
                "Aceitou receber contato do BLH mais proximo de casa."
            ),
            messages = listOf(
                LaraMessage(2, "Acolhimento", "Que bom te acompanhar de novo, Aline. Como foi a volta para casa?"),
                LaraMessage(10, "Convite", "Voce comentou que ja doou antes. Posso te conectar ao BLH de Osasco?"),
                LaraMessage(18, "Conversao", "Agendamento confirmado para hoje as 16h.")
            ),
            needsHumanSupport = false
        ),
        NutrizJourney(
            id = 4,
            name = "Juliana Martins",
            city = "Sao Paulo",
            homeAddress = "Itaquera, Sao Paulo",
            babyProfile = "Gemeos, mae relata ansiedade e dificuldade para descansar.",
            postpartumDay = 9,
            currentStage = JourneyStage.Education,
            readinessScore = 18,
            sentiment = Sentiment.Hesitant,
            nearestMilkBank = MilkBank(
                name = "BLH Hospital Municipal Tide Setubal",
                distanceKm = 3.1,
                phone = "(11) 2555-9090",
                nextAvailableSlot = "Quinta-feira, 9h"
            ),
            emotionalMemory = listOf(
                "Disse que nao quer falar de doacao neste momento.",
                "Perguntou se a propria amamentacao dos gemeos pode ser prejudicada.",
                "Score abaixo de 20 recomenda handoff humano com contexto."
            ),
            messages = listOf(
                LaraMessage(2, "Acolhimento", "Juliana, cuidar de gemeos exige muito. Hoje eu so quero saber como voce esta."),
                LaraMessage(8, "Educacao", "Doar nunca deve colocar sua amamentacao em risco. Posso chamar alguem para conversar com calma?")
            ),
            needsHumanSupport = true
        )
    )

    fun createJourney(form: NutrizForm, nextId: Int): NutrizJourney {
        return NutrizJourney(
            id = nextId,
            name = form.name.trim(),
            city = form.city.trim(),
            homeAddress = form.homeAddress.trim(),
            babyProfile = form.babyProfile.trim(),
            postpartumDay = 0,
            currentStage = JourneyStage.Birth,
            readinessScore = 12,
            sentiment = Sentiment.Tired,
            nearestMilkBank = MilkBank(
                name = "BLH parceiro mais proximo da casa",
                distanceKm = 2.0,
                phone = "(11) 0000-0000",
                nextAvailableSlot = "A definir apos acolhimento"
            ),
            emotionalMemory = listOf(
                "Nascimento registrado por hospital parceiro.",
                "Nenhuma mensagem de doacao enviada no dia 0, respeitando o momento da mae."
            ),
            messages = listOf(
                LaraMessage(0, "Parto", "Registro criado. A LARA aguardara 48h para iniciar o acolhimento.")
            ),
            needsHumanSupport = false
        )
    }
}
