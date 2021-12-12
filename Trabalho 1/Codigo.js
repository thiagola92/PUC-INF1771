// Tempo maximo que os cavaleiros podem demorar
var TEMPO_MAXIMO = 720;

// LINES x COLUMNS não deve exceder o numero de palavras na string
var LINHAS = 42;     // Numero de Linhas que o mapa tem
var COLUNAS = 42;    // Numero de Colunas que o mapa tem

// Custo de cada tipo de terreno
var PLANO = 1;
var ROCHOSO = 5;
var MONTANHOSO = 200;

// Cosmicos de cada cavaleiro
var PODER_DE_LUTA = [1.5, 1.4, 1.3, 1.2, 1.1];

// Custo de cada cavaleiro de ouro
var CASA = [50, 55, 60, 70, 75, 80, 85, 90, 95, 100, 110, 120];

/*
Tradução do mapa

M = Montanhoso
P = Plano
R = Rochoso

0 = Casa de Áries
1 = Casa de Touro
2 = Casa de Gêmeos
3 = Casa de Câncer
4 = Casa de Leão
5 = Casa de Virgem
6 = Casa de Libra
7 = Casa de Escorpião
8 = Casa de Sagitário
9 = Casa de Capricórnio
A = Casa de Aquário
B = Casa de Peixes

I = Inicio/Local atual
F = Fim
*/

var MAPA =
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRRRRRRRRRRMRRRRPPRPRPRRRRRRMRRRRRRRRMMM" +
         "MMMRPPPRPRPRRARRPRRRRPRPRPPPRRBRRRRRRFRMMM" +
         "MMMRRRRRPRPRRMRRPRPPRRRRRPRRRRMRRRRRRRRMMM" +
         "MMMRPPMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRPRMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRPRRPRRRRRRMRRRRRPRPRRRRMRRRRRPRRPRRMMM" +
         "MMMRPRRPRPPPRR9RRPRRPRPPPRR8RRPRRPRRPPRMMM" +
         "MMMRRRRRRRRPRRMRRPRRRRRRPRRMRRPRRRRRRRRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMRPRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMRRRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM7MMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMRRRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMRRRMMM" +
         "MMMRRRRPRMRRPRRRRRPRRRRRPRRRRMRRRRPPRPRMMM" +
         "MMMRRRRPR5RPPRRPRRPRPPRRPRPPR6RRPRRPRPRMMM" +
         "MMMPPRRRRMRRRRRPRRRRRPRRRRRRRMRRPRRRRRRMMM" +
         "MMMRRRMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMPPRMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRRRMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRPRMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRPRRRRMRRRRRPRRRRRRRPRRMRRRPRRPRRRRRMMM" +
         "MMMRPPRPR4RRPPRPPPRPPPRPPR3RPRPPRPRPRRRMMM" +
         "MMMRRRRPRMRRRRRRRPRPRRRRRRMRRRRRRRRPPPRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMRRRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMPPRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMRRRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMPPRMMM" +
         "MMMRRRRPRRRRPRRRRMRRRRRRRPPPRRRPRMRPRPRMMM" +
         "MMMPPPRPRPRRPRPPR1RRPRPPRPRRRRRPR2RPRPRMMM" +
         "MMMRRRRRRPRRRRRRRMRRPRRPRPRRPPRRRMRRRRRMMM" +
         "MMMRPPMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRRRMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRPPMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMRRPRRRRRRPRRRPRRPRMRRRRRRPRRRRRRRRRRMMM" +
         "MMMRRPPPRPRRPRRPPPRPR0RPRPPRPRPRRPRRRIRMMM" +
         "MMMRRRRRRPRRRRRRRRRPRMRPRRRRRRRRRPRRRRRMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" +
         "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM";

/************************************************************ DEBUGS ********************************************************/

var DEBUG_gerarMapaNaTela = false;
var DEBUG_gerarMatrixDoMapa = false;
var DEUBG_valorDaLetra = false;
var DEBUG_calcularDistanciasProFim = false;
var DEBUG_criarFolha = false;
var DEBUG_desenharNaTela = false;
var DEBUG_f = false;
var DEBUG_expandirParaOsLados = false;
var DEBUG_copiarFolha = false;
var DEBUG_todasLutasPossiveisDosCavaleiros = false;
var DEBUG_deletarFolha = false;
var DEBUG_procurarLetra = false;
var DEBUG_atualizarMapaNaTela = false;
var DEBUG_deletarCiclos = false;
var DEBUG_mostraCaminho = false;
var DEBUG_deletarFolhaComCavaleirosMortos = false;
var DEBUG_deletarClones = false;

/*********************************************** VARIÁVEIS QUE NÃO SE DEVEM ALTERAR *****************************************/

var mapa_ja_criado = false;
var casas_valem_1 = true;

var matrix_do_mapa = [];
var matrix_do_peso_das_distancias = [];
var melhor_tempo_nesse_lugar = [];

var folhas_da_arvore = [];

var posicao_x_do_fim;
var posicao_y_do_fim;

var posicao_x_do_inicio;
var posicao_y_do_inicio;


/************************************************************ FUNÇÕES *****************************************************/

function gerarMapaNaTela() {
    if (DEBUG_gerarMapaNaTela) {
        console.log("~~~~ gerarMapaNaTela ~~~~");
        console.log("TEMPO_MAXIMO: " + TEMPO_MAXIMO);
        console.log("LINHAS: " + LINHAS);
        console.log("COLUNAS: " + COLUNAS);
        console.log("PLANO: " + PLANO);
        console.log("ROCHOSO: " + ROCHOSO);
        console.log("MONTANHOSO: " + MONTANHOSO);
        console.log("PODER_DE_LUTA: " + PODER_DE_LUTA);
        console.log("TEMPO_MAXIMO: " + TEMPO_MAXIMO);
        console.log("CASA: " + CASA);
    }

    matrix_do_mapa.length = 0;
    matrix_do_peso_das_distancias.length = 0;

    gerarMatrixDoMapa();
    calcularDistanciasProFim(posicao_x_do_fim, posicao_y_do_fim);
    
    criarFolha();
    folhas_da_arvore[0].posicao_x_da_folha = posicao_x_do_inicio;
    folhas_da_arvore[0].posicao_y_da_folha = posicao_y_do_inicio;
    folhas_da_arvore[0].ultimo_movimento[0] = "Start";

    desenharNaTela();

    mapa_ja_criado = true;


    if (melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] > folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]) {

        if (DEBUG_expandirParaOsLados) {
            console.log("Achou alguem com o tempo melhor");
            console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
        }

        melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] = folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha];

        if (DEBUG_expandirParaOsLados)
            console.log("Seu novo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
    } else {
        if (DEBUG_expandirParaOsLados) {
            console.log("Deletando esse caminho pois existe um melhor até aquele ponto");
            console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
        }

        folhas_da_arvore.length--;


    }

    if (DEBUG_gerarMapaNaTela) {
        mostrarNoConsoleTodaAMatrixDeDistancia();
        console.log("^^^^ gerarMapaNaTela ^^^^");
    }
}

function gerarMatrixDoMapa() {
    var linha, coluna;

    if (DEBUG_gerarMatrixDoMapa)
        console.log("~~~~ gerarMatrixDoMapa ~~~~");

    for (linha = 0; linha < LINHAS; linha++) {
        matrix_do_mapa[linha] = [];
        matrix_do_peso_das_distancias[linha] = [];
        melhor_tempo_nesse_lugar[linha] = [];
        for (coluna = 0; coluna < COLUNAS; coluna++) {
            matrix_do_mapa[linha][coluna] = MAPA.charAt(COLUNAS * linha + coluna);
            matrix_do_peso_das_distancias[linha][coluna] = -1;
            melhor_tempo_nesse_lugar[linha][coluna] = MONTANHOSO * MONTANHOSO;

            if (matrix_do_mapa[linha][coluna] == "F") {
                posicao_x_do_fim = linha;
                posicao_y_do_fim = coluna;
            }

            if (matrix_do_mapa[linha][coluna] == "I") {
                posicao_x_do_inicio = linha;
                posicao_y_do_inicio = coluna;
            }
        }
    }

    if (DEBUG_gerarMatrixDoMapa) {
        console.log("linhas lidas: " + linha);
        console.log("colunas lidas: " + coluna);

        console.log("linhas da matrix_do_mapa: " + matrix_do_mapa.length);
        console.log("colunas da matrix_do_mapa: " + matrix_do_mapa[0].length);

        console.log("linhas da matrix_do_peso_das_distancias: " + matrix_do_peso_das_distancias.length);
        console.log("colunas da matrix_do_peso_das_distancias: " + matrix_do_peso_das_distancias[0].length);

        console.log("posicao_x_do_fim e posicao_y_do_fim: " + posicao_x_do_fim + " e " + posicao_y_do_fim);
        console.log("posicao_x_do_inicio e posicao_y_do_inicio: " + posicao_x_do_inicio + " e " + posicao_y_do_inicio);

        console.log("^^^^ gerarMatrixDoMapa ^^^^");
    }
}

function calcularDistanciasProFim(posicao_x, posicao_y) {
    var valor_da_casa;
    var valor_da_casa_do_lado;

    if (DEBUG_calcularDistanciasProFim) {
        console.log("~~~~ calcularDistanciasProFim ~~~~");

        console.log("matrix_do_peso_das_distancias[" + posicao_x + "][" + posicao_y + "]: " + matrix_do_peso_das_distancias[posicao_x][posicao_y]);
        console.log("valor_da_casa: " + valorDaLetra(posicao_x, posicao_y));
    }

    valor_da_casa = valorDaLetra(posicao_x, posicao_y);
    casa_valem_0 = false;

    if (valor_da_casa == 0) {
        matrix_do_peso_das_distancias[posicao_x][posicao_y] = 0;

        if (DEBUG_calcularDistanciasProFim)
            console.log("matrix_do_peso_das_distancias[" + posicao_x + "][" + posicao_y + "]: " + matrix_do_peso_das_distancias[posicao_x][posicao_y]);
        
        if (posicao_x - 1 >= 0)
            calcularDistanciasProFim(posicao_x - 1, posicao_y);
        if (posicao_x + 1 <= LINHAS - 1)
            calcularDistanciasProFim(posicao_x + 1, posicao_y);
        if (posicao_y - 1 >= 0)
            calcularDistanciasProFim(posicao_x, posicao_y - 1);
        if (posicao_y + 1 <= COLUNAS - 1)
            calcularDistanciasProFim(posicao_x, posicao_y + 1);
    } else {

        if (posicao_x - 1 >= 0) {
            if (matrix_do_peso_das_distancias[posicao_x - 1][posicao_y] != -1)
                if ((matrix_do_peso_das_distancias[posicao_x - 1][posicao_y] + valor_da_casa < matrix_do_peso_das_distancias[posicao_x][posicao_y]) || matrix_do_peso_das_distancias[posicao_x][posicao_y] == -1)
                    matrix_do_peso_das_distancias[posicao_x][posicao_y] = matrix_do_peso_das_distancias[posicao_x - 1][posicao_y] + valor_da_casa;
        }

        if (posicao_x + 1 <= LINHAS - 1) {
            if (matrix_do_peso_das_distancias[posicao_x + 1][posicao_y] != -1)
                if ((matrix_do_peso_das_distancias[posicao_x + 1][posicao_y] + valor_da_casa < matrix_do_peso_das_distancias[posicao_x][posicao_y]) || matrix_do_peso_das_distancias[posicao_x][posicao_y] == -1)
                    matrix_do_peso_das_distancias[posicao_x][posicao_y] = matrix_do_peso_das_distancias[posicao_x + 1][posicao_y] + valor_da_casa;
        }

        if (posicao_y - 1 >= 0) {
            if (matrix_do_peso_das_distancias[posicao_x][posicao_y - 1] != -1)
                if ((matrix_do_peso_das_distancias[posicao_x][posicao_y - 1] + valor_da_casa < matrix_do_peso_das_distancias[posicao_x][posicao_y]) || matrix_do_peso_das_distancias[posicao_x][posicao_y] == -1)
                    matrix_do_peso_das_distancias[posicao_x][posicao_y] = matrix_do_peso_das_distancias[posicao_x][posicao_y - 1] + valor_da_casa;
        }

        if (posicao_y + 1 <= COLUNAS - 1) {
            if (matrix_do_peso_das_distancias[posicao_x][posicao_y + 1] != -1)
                if ((matrix_do_peso_das_distancias[posicao_x][posicao_y + 1] + valor_da_casa < matrix_do_peso_das_distancias[posicao_x][posicao_y]) || matrix_do_peso_das_distancias[posicao_x][posicao_y] == -1)
                    matrix_do_peso_das_distancias[posicao_x][posicao_y] = matrix_do_peso_das_distancias[posicao_x][posicao_y + 1] + valor_da_casa;
        }
    }

    if (DEBUG_calcularDistanciasProFim)
        console.log("matrix_do_peso_das_distancias[" + posicao_x + "][" + posicao_y + "]: " + matrix_do_peso_das_distancias[posicao_x][posicao_y]);
    
    if (posicao_x - 1 >= 0) {
        valor_da_casa_do_lado = valorDaLetra(posicao_x - 1, posicao_y);
        if (matrix_do_peso_das_distancias[posicao_x - 1][posicao_y] != -1) {
            if (matrix_do_peso_das_distancias[posicao_x][posicao_y] + valor_da_casa_do_lado < matrix_do_peso_das_distancias[posicao_x - 1][posicao_y])
                calcularDistanciasProFim(posicao_x - 1, posicao_y);
        } else
            calcularDistanciasProFim(posicao_x - 1, posicao_y);
    }

    if (posicao_x + 1 <= LINHAS - 1) {
        valor_da_casa_do_lado = valorDaLetra(posicao_x + 1, posicao_y);
        if (matrix_do_peso_das_distancias[posicao_x + 1][posicao_y] != -1) {
            if (matrix_do_peso_das_distancias[posicao_x][posicao_y] + valor_da_casa_do_lado < matrix_do_peso_das_distancias[posicao_x + 1][posicao_y])
                calcularDistanciasProFim(posicao_x + 1, posicao_y);
        } else
            calcularDistanciasProFim(posicao_x + 1, posicao_y);
    }

    if (posicao_y - 1 >= 0) {
        valor_da_casa_do_lado = valorDaLetra(posicao_x, posicao_y - 1);
        if (matrix_do_peso_das_distancias[posicao_x][posicao_y - 1] != -1) {
            if (matrix_do_peso_das_distancias[posicao_x][posicao_y] + valor_da_casa_do_lado < matrix_do_peso_das_distancias[posicao_x][posicao_y - 1])
                calcularDistanciasProFim(posicao_x, posicao_y - 1);
        } else
            calcularDistanciasProFim(posicao_x, posicao_y - 1);
    }

    if (posicao_y + 1 <= COLUNAS - 1) {
        valor_da_casa_do_lado = valorDaLetra(posicao_x, posicao_y + 1);
        if (matrix_do_peso_das_distancias[posicao_x][posicao_y + 1] != -1) {
            if (matrix_do_peso_das_distancias[posicao_x][posicao_y] + valor_da_casa_do_lado < matrix_do_peso_das_distancias[posicao_x][posicao_y + 1])
                calcularDistanciasProFim(posicao_x, posicao_y + 1);
        } else
            calcularDistanciasProFim(posicao_x, posicao_y + 1);
    }

    if (DEBUG_calcularDistanciasProFim) {

        console.log("^^^^ calcularDistanciasProFim ^^^^");
    }
}

function valorDaLetra(posicao_x, posicao_y) {
    
    var retorna;

    if (DEUBG_valorDaLetra)
        console.log("~~~~ valorDaLetra ~~~~");

    if (casas_valem_1 == true) {
        if (matrix_do_mapa[posicao_x][posicao_y] == "0" ||
            matrix_do_mapa[posicao_x][posicao_y] == "1" ||
            matrix_do_mapa[posicao_x][posicao_y] == "2" ||
            matrix_do_mapa[posicao_x][posicao_y] == "3" ||
            matrix_do_mapa[posicao_x][posicao_y] == "4" ||
            matrix_do_mapa[posicao_x][posicao_y] == "5" ||
            matrix_do_mapa[posicao_x][posicao_y] == "6" ||
            matrix_do_mapa[posicao_x][posicao_y] == "7" ||
            matrix_do_mapa[posicao_x][posicao_y] == "8" ||
            matrix_do_mapa[posicao_x][posicao_y] == "9" ||
            matrix_do_mapa[posicao_x][posicao_y] == "A" ||
            matrix_do_mapa[posicao_x][posicao_y] == "B") {

            if (DEUBG_valorDaLetra) {
                console.log("retorna: " + 0);
                console.log("^^^^ valorDaLetra ^^^^");
            }

            return 1;
        }
    }


    if (matrix_do_mapa[posicao_x][posicao_y] == "P")
        retorna = PLANO;
    else if (matrix_do_mapa[posicao_x][posicao_y] == "R")
        retorna = ROCHOSO;
    else if (matrix_do_mapa[posicao_x][posicao_y] == "M")
        retorna = MONTANHOSO;
    else if (matrix_do_mapa[posicao_x][posicao_y] == "F")
        retorna = 0;
    else if (matrix_do_mapa[posicao_x][posicao_y] == "I")
        retorna = PLANO;
    else if (matrix_do_mapa[posicao_x][posicao_y] == "0")
        retorna = CASA[0];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "1")
        retorna = CASA[1];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "2")
        retorna = CASA[2];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "3")
        retorna = CASA[3];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "4")
        retorna = CASA[4];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "5")
        retorna = CASA[5];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "6")
        retorna = CASA[6];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "7")
        retorna = CASA[7];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "8")
        retorna = CASA[8];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "9")
        retorna = CASA[9];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "A")
        retorna = CASA[10];
    else if (matrix_do_mapa[posicao_x][posicao_y] == "B")
        retorna = CASA[11];

    if (DEUBG_valorDaLetra) {
        console.log("retorna: " + retorna);
        console.log("^^^^ valorDaLetra ^^^^");
    }

    return retorna;
}

function criarFolha() {

    if (DEBUG_criarFolha) {
        console.log("~~~~ criarFolha ~~~~");
        console.log("quantidade de folhas antes: ", folhas_da_arvore.length);
    }

    folhas_da_arvore[folhas_da_arvore.length] = {
        posicao_x_da_folha: -1,
        posicao_y_da_folha: -1,

        vida_dos_cavaleiros: [5, 5, 5, 5, 5],

        tempo_caminhando: 0,

        ultimo_movimento: [],
    }

    if (DEBUG_criarFolha) {
        console.log("quantidade de folhas depois: ", folhas_da_arvore.length);

        console.log("posicao_x_da_folha: ", folhas_da_arvore[folhas_da_arvore.length-1].posicao_x_da_folha);
        console.log("posicao_y_da_folha: ", folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha);

        console.log("vida_dos_cavaleiros: ", folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros);

        console.log("tempo_caminhando: ", folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando);

        console.log("ultimo_movimento: ", folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento);

        console.log("^^^^ criarFolha ^^^^");
    }
}

function desenharNaTela() {
    var linha, coluna, html_da_tabela;

    if (DEBUG_desenharNaTela)
        console.log("~~~~ desenharNaTela ~~~~");

    html_da_tabela = "<table>";

    for (linha = 0; linha < LINHAS; linha++) {
        html_da_tabela += "<tr>";

        for (coluna = 0; coluna < COLUNAS; coluna++) {
            if (matrix_do_mapa[linha][coluna] == "M")
                html_da_tabela += "<td><img src='\M.png'></td>";
            else if (matrix_do_mapa[linha][coluna] == "P")
                html_da_tabela += "<td><img src='\P.png'></td>";
            else if (matrix_do_mapa[linha][coluna] == "R")
                html_da_tabela += "<td><img src='\R.png'></td>";
            else if (matrix_do_mapa[linha][coluna] == "F")
                html_da_tabela += "<td><img src='\F.png'></td>";
            else if (matrix_do_mapa[linha][coluna] == "I")
                html_da_tabela += "<td><img src='\I.png'></td>";
            else if ((matrix_do_mapa[linha][coluna] == "0") ||
                (matrix_do_mapa[linha][coluna] == "1") ||
                (matrix_do_mapa[linha][coluna] == "2") ||
                (matrix_do_mapa[linha][coluna] == "3") ||
                (matrix_do_mapa[linha][coluna] == "4") ||
                (matrix_do_mapa[linha][coluna] == "5") ||
                (matrix_do_mapa[linha][coluna] == "6") ||
                (matrix_do_mapa[linha][coluna] == "7") ||
                (matrix_do_mapa[linha][coluna] == "8") ||
                (matrix_do_mapa[linha][coluna] == "9") ||
                (matrix_do_mapa[linha][coluna] == "A") ||
                (matrix_do_mapa[linha][coluna] == "B"))
                html_da_tabela += "<td><img src='\C.png'></td>";
            else
                alert("Caracter inválido na linha " + linha + " e coluna " + coluna);
        }

        html_da_tabela += "</tr>"
    }

    html_da_tabela += "</table>";
    document.getElementById("mapa").innerHTML = html_da_tabela;

    if (DEBUG_desenharNaTela)
        console.log("^^^^ desenharNaTela ^^^^");
}

function atualizarMapaNaTela() {
    var posicao_x_do_i;
    var posicao_y_do_i;

    if (DEBUG_atualizarMapaNaTela)
        console.log("~~~~ atualizarMapaNaTela ~~~~");

    posicao_x_do_i = procurarLetra("x", "I");
    posicao_y_do_i = procurarLetra("y", "I");

    if (DEBUG_atualizarMapaNaTela) {
        console.log("posicao_x_do_i: " + posicao_x_do_i);
        console.log("posicao_y_do_i: " + posicao_y_do_i);
    }

    if (MAPA.charAt(COLUNAS * posicao_x_do_i + posicao_y_do_i) == "I")
        matrix_do_mapa[posicao_x_do_i][posicao_y_do_i] = "P";
    else
        matrix_do_mapa[posicao_x_do_i][posicao_y_do_i] = MAPA.charAt(COLUNAS * posicao_x_do_i + posicao_y_do_i);

    matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha] = "I";

    document.getElementById("cavaleiros").innerHTML = "<br>Seiya [" + folhas_da_arvore[0].vida_dos_cavaleiros[0] + "]<br>Shiryu [" + folhas_da_arvore[0].vida_dos_cavaleiros[1] + "]<br>Hyoga [" + folhas_da_arvore[0].vida_dos_cavaleiros[2] + "]<br>Shun [" + folhas_da_arvore[0].vida_dos_cavaleiros[3] + "]<br>Ikki [" + folhas_da_arvore[0].vida_dos_cavaleiros[4] + "]";
    document.getElementById("tempo").innerHTML = "<br>" + folhas_da_arvore[0].tempo_caminhando;
    document.getElementById("somatorio").innerHTML = "<br>" + (folhas_da_arvore[0].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha]);
    document.getElementById("distancia").innerHTML = "<br>" + matrix_do_peso_das_distancias[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha];

    desenharNaTela();

    if (DEBUG_atualizarMapaNaTela)
        console.log("^^^^ atualizarMapaNaTela ^^^^");
}

function mostraCaminho() {

    var x = folhas_da_arvore[0].posicao_x_da_folha;
    var y = folhas_da_arvore[0].posicao_y_da_folha;
    var index = folhas_da_arvore[0].ultimo_movimento.length - 1;
    if (DEBUG_mostraCaminho) {
        console.log(" ~~~ mostraCaminho ~~~ ");
    }

    while (folhas_da_arvore[0].ultimo_movimento[index] != "Start") {
        matrix_do_mapa[x][y] = "I";

        if (folhas_da_arvore[0].ultimo_movimento[index] == "Cima")
            x++;
        else if (folhas_da_arvore[0].ultimo_movimento[index] == "Baixo")
            x--;
        else if (folhas_da_arvore[0].ultimo_movimento[index] == "Esquerda")
            y++;
        else if (folhas_da_arvore[0].ultimo_movimento[index] == "Direita")
            y--;

        index--;
    }

    matrix_do_mapa[x][y] = "I";
    desenharNaTela();

    if (DEBUG_mostraCaminho) {
        console.log(" ^^^ mostraCaminho ^^^ ");
    }

}

function procurarLetra(x_ou_y, letra) {
    var linha, coluna;

    if (DEBUG_procurarLetra)
        console.log("~~~~ procurarLetra ~~~~");

    for (linha = 0; linha < LINHAS; linha++) {
        for (coluna = 0; coluna < COLUNAS; coluna++) {
            if (matrix_do_mapa[linha][coluna] == letra) {
                if (x_ou_y == "x") {
                    if (DEBUG_procurarLetra) {

                        console.log(letra + "|" + linha);
                        console.log("^^^^ procurarLetra ^^^^");
                    }

                    return linha;
                } else if (x_ou_y == "y") {

                    if (DEBUG_procurarLetra) {
                        console.log(letra + "|" + coluna);
                        console.log("^^^^ procurarLetra ^^^^");
                    }

                    return coluna;
                }

            }
        }
    }

    if (DEBUG_procurarLetra)
        console.log("^^^^ procurarLetra ^^^^");

}

function copiarFolha() {
    var contador_folha;

    if (DEBUG_copiarFolha)
        console.log("~~~~ copiarFolha ~~~~");

    folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha = folhas_da_arvore[0].posicao_x_da_folha;
    folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha = folhas_da_arvore[0].posicao_y_da_folha;

    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[0] = folhas_da_arvore[0].vida_dos_cavaleiros[0];
    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[1] = folhas_da_arvore[0].vida_dos_cavaleiros[1];
    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[2] = folhas_da_arvore[0].vida_dos_cavaleiros[2];
    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[3] = folhas_da_arvore[0].vida_dos_cavaleiros[3];
    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[4] = folhas_da_arvore[0].vida_dos_cavaleiros[4];

    folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando = folhas_da_arvore[0].tempo_caminhando;

    for (contador_folha = 0; contador_folha < folhas_da_arvore[0].ultimo_movimento.length; contador_folha++) {
        folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[contador_folha] = folhas_da_arvore[0].ultimo_movimento[contador_folha];
    }

    if (DEBUG_copiarFolha) {
        console.log("Folha da posição: " + 0);
        console.log(folhas_da_arvore[0]);
        console.log("Folha da posição: " + (folhas_da_arvore.length - 1));
        console.log(folhas_da_arvore[folhas_da_arvore.length - 1]);

        console.log("^^^^ copiarFolha ^^^^");
    }
}

function somatorioDosCavaleiros(quem_vai_lutar) {
    return PODER_DE_LUTA[0] * quem_vai_lutar[0] + PODER_DE_LUTA[1] * quem_vai_lutar[1] + PODER_DE_LUTA[2] * quem_vai_lutar[2] + PODER_DE_LUTA[3] * quem_vai_lutar[3] + PODER_DE_LUTA[4] * quem_vai_lutar[4];
}

function todasLutasPossiveisDosCavaleiros(cavaleiros_que_vao_brigar, deslocamento_x, deslocamento_y, movimento_feito) {
    if (DEBUG_todasLutasPossiveisDosCavaleiros)
        console.log("~~~~ todasLutasPossiveisDosCavaleiros ~~~~");

    criarFolha();
    copiarFolha();
    folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha += deslocamento_x;
    folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha += deslocamento_y;

    var casa = matrix_do_mapa[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha];

    if (casa == "0" || casa == "1") {
        cavaleiros_que_vao_brigar[2] = 1;
        cavaleiros_que_vao_brigar[4] = 1;
    }

    else if (casa == "2" || casa == "3") {
        cavaleiros_que_vao_brigar[1] = 1;
        cavaleiros_que_vao_brigar[4] = 1;
    }

    else if (casa == "4" || casa == "5" || casa == "6") {
        cavaleiros_que_vao_brigar[1] = 1;
        cavaleiros_que_vao_brigar[3] = 1;
    }

    else if (casa == "7" || casa == "8") {
        cavaleiros_que_vao_brigar[0] = 1;
        cavaleiros_que_vao_brigar[3] = 1;
    }

    else if (casa == "9" || casa == "A" || casa == "B") {
        cavaleiros_que_vao_brigar[0] = 1;
        cavaleiros_que_vao_brigar[2] = 1;
    }


    if (DEBUG_todasLutasPossiveisDosCavaleiros) {
        console.log("1 se o cavaleiro for lutar, 0 se não:")
        console.log(cavaleiros_que_vao_brigar);
    }

    folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando += valorDaLetra(folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha, folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha) / somatorioDosCavaleiros(cavaleiros_que_vao_brigar);

    folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento.length] = movimento_feito;

    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[0] -= cavaleiros_que_vao_brigar[0];
    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[1] -= cavaleiros_que_vao_brigar[1];
    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[2] -= cavaleiros_que_vao_brigar[2];
    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[3] -= cavaleiros_que_vao_brigar[3];
    folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[4] -= cavaleiros_que_vao_brigar[4];

    if (DEBUG_todasLutasPossiveisDosCavaleiros) {
        console.log(folhas_da_arvore[folhas_da_arvore.length - 1]);
        console.log(folhas_da_arvore.length);
        console.log(folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[0] + "|" + folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[1] + "|" + folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[2] + "|" + folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[3] + "|" + folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[4]);
        console.log(cavaleiros_que_vao_brigar[0] + "|" + cavaleiros_que_vao_brigar[1] + "|" + cavaleiros_que_vao_brigar[2] + "|" + cavaleiros_que_vao_brigar[3] + "|" + cavaleiros_que_vao_brigar[4]);
    }

    deletarCiclos();
    deletarFolhaComCavaleirosMortos();

    if (DEBUG_todasLutasPossiveisDosCavaleiros)
        console.log("^^^^ todasLutasPossiveisDosCavaleiros ^^^^");
}

function deletarFolha() {
    var folha_atual;

    if (DEBUG_deletarFolha)
        console.log("~~~~ deletarFolha ~~~~");

    for (folha_atual = 0; folha_atual < (folhas_da_arvore.length - 1) ; folha_atual++) {
        folhas_da_arvore[folha_atual] = folhas_da_arvore[folha_atual+1];
    }

    folhas_da_arvore.length--;

    if (DEBUG_deletarFolha) {
        alert("Tamnho da folhas_da_arvore ficou negativo");
        console.log("^^^^ deletarFolha ^^^^");
    }

}

function deletarCiclos() {
    var tamanho;

    var passos_para_x=0;
    var passos_para_y=0;

    if (DEBUG_deletarCiclos)
        console.log("~~~~ deletarCiclos ~~~~");

    for (tamanho = folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento.length - 1; tamanho > 1; tamanho--) {

        if (folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[tamanho] == "Cima")
            passos_para_x++;
        else if (folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[tamanho] == "Baixo")
            passos_para_x--;
        else if (folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[tamanho] == "Esquerda")
            passos_para_y++;
        else if (folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[tamanho] == "Direita")
            passos_para_y--;

        if ( passos_para_x == 0 && passos_para_y == 0) {
            folhas_da_arvore.length--;
            if (DEBUG_deletarCiclos) {
                console.log("passos_para_x: " + passos_para_x + "| passos_para_y: " + passos_para_y);
                console.log("Um ciclo foi encontrado e excluido");
            }
        }

    }

    if (DEBUG_deletarCiclos)
        console.log("^^^^ deletarCiclos ^^^^");
}

function deletarFolhaComCavaleirosMortos() {

    if (DEBUG_deletarFolhaComCavaleirosMortos)
        console.log("~~~~ deletarFolhaComCavaleirosMortos ~~~~");

    if (folhas_da_arvore[folhas_da_arvore.length-1].vida_dos_cavaleiros[0] < 0 ||
        folhas_da_arvore[folhas_da_arvore.length-1].vida_dos_cavaleiros[1] < 0 ||
        folhas_da_arvore[folhas_da_arvore.length-1].vida_dos_cavaleiros[2] < 0 ||
        folhas_da_arvore[folhas_da_arvore.length-1].vida_dos_cavaleiros[3] < 0 ||
        folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[4] < 0) {
        folhas_da_arvore.length--;

        if (DEBUG_deletarFolhaComCavaleirosMortos)
            console.log("Uma folha foi deletada pois um dos cavaleiros estava com vida negativa");

    } else if (folhas_da_arvore[folhas_da_arvore.length-1].vida_dos_cavaleiros[0] == 0 &&
        folhas_da_arvore[folhas_da_arvore.length-1].vida_dos_cavaleiros[1] == 0 &&
        folhas_da_arvore[folhas_da_arvore.length-1].vida_dos_cavaleiros[2] == 0 &&
        folhas_da_arvore[folhas_da_arvore.length-1].vida_dos_cavaleiros[3] == 0 &&
        folhas_da_arvore[folhas_da_arvore.length - 1].vida_dos_cavaleiros[4] == 0) {
        folhas_da_arvore.length--;

        if (DEBUG_deletarFolhaComCavaleirosMortos)
            console.log("Uma folha foi deletada pois os 5 cavaleiros estavam mortos");
    }

    if (DEBUG_deletarFolhaComCavaleirosMortos)
        console.log("^^^^ deletarFolhaComCavaleirosMortos ^^^^");
}

function deletarClones() {
    var contador = 0;
    var contador2 = 0;
    var contador3 = 0;

    if (DEBUG_deletarClones)
        console.log("^^^^ deletarClones ^^^^");

    for (contador = 0; contador < folhas_da_arvore.length - 1; contador++) {

        for (contador2 = contador+1; contador2 < folhas_da_arvore.length - 1; contador2++) {

            if (folhas_da_arvore[contador].posicao_x_da_folha == folhas_da_arvore[contador2].posicao_x_da_folha &&
                folhas_da_arvore[contador].posicao_y_da_folha == folhas_da_arvore[contador2].posicao_y_da_folha &&
                folhas_da_arvore[contador].vida_dos_cavaleiros[0] == folhas_da_arvore[contador2].vida_dos_cavaleiros[0] &&
                folhas_da_arvore[contador].vida_dos_cavaleiros[1] == folhas_da_arvore[contador2].vida_dos_cavaleiros[1] &&
                folhas_da_arvore[contador].vida_dos_cavaleiros[2] == folhas_da_arvore[contador2].vida_dos_cavaleiros[2] &&
                folhas_da_arvore[contador].vida_dos_cavaleiros[3] == folhas_da_arvore[contador2].vida_dos_cavaleiros[3] &&
                folhas_da_arvore[contador].vida_dos_cavaleiros[4] == folhas_da_arvore[contador2].vida_dos_cavaleiros[4] &&
                folhas_da_arvore[contador].tempo_caminhando == folhas_da_arvore[contador2].tempo_caminhando) {


                if (DEBUG_deletarClones) {
                    console.log("Clone encontrado");
                    console.log(folhas_da_arvore[contador]);
                    console.log(folhas_da_arvore[contador2]);
                }

                for (contador3 = contador2; contador3 + 1 < folhas_da_arvore.length; contador3++) {
                    

                    folhas_da_arvore[contador3].posicao_x_da_folha = folhas_da_arvore[contador3 + 1].posicao_x_da_folha;
                    folhas_da_arvore[contador3].posicao_y_da_folha = folhas_da_arvore[contador3 + 1].posicao_y_da_folha;

                    folhas_da_arvore[contador3].vida_dos_cavaleiros[0] = folhas_da_arvore[contador3+1].vida_dos_cavaleiros[0];
                    folhas_da_arvore[contador3].vida_dos_cavaleiros[1] = folhas_da_arvore[contador3 + 1].vida_dos_cavaleiros[1];
                    folhas_da_arvore[contador3].vida_dos_cavaleiros[2] = folhas_da_arvore[contador3 + 1].vida_dos_cavaleiros[2];
                    folhas_da_arvore[contador3].vida_dos_cavaleiros[3] = folhas_da_arvore[contador3 + 1].vida_dos_cavaleiros[3];
                    folhas_da_arvore[contador3].vida_dos_cavaleiros[4] = folhas_da_arvore[contador3 + 1].vida_dos_cavaleiros[4];

                    folhas_da_arvore[contador3].tempo_caminhando = folhas_da_arvore[contador3 + 1].tempo_caminhando;

                    for (contador4 = 0; contador4 < folhas_da_arvore[contador3 + 1].ultimo_movimento.length; contador4++) {
                        folhas_da_arvore[contador3].ultimo_movimento[contador4] = folhas_da_arvore[contador3+1].ultimo_movimento[contador4];
                    }

                    folhas_da_arvore[contador3].ultimo_movimento.length = folhas_da_arvore[contador3+1].ultimo_movimento.length;

                }

            }

        }

    }

    if (DEBUG_deletarClones)
        console.log("^^^^ deletarClones ^^^^");

}

function mostrarNoConsoleTodasAsFolhas() {
    var folha_atual;

    console.log("Quantidade de folhas na arvore: " + folhas_da_arvore.length);

    for (folha_atual = 0; folha_atual < folhas_da_arvore.length ; folha_atual++) {
        console.log(folhas_da_arvore[folha_atual]);
        console.log("Somatorio: " + (folhas_da_arvore[folha_atual].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folha_atual].posicao_x_da_folha][folhas_da_arvore[folha_atual].posicao_y_da_folha]));
    }
}

function mostrarNoConsoleTodaAMatrixDeDistancia() {
    var linha;

    for (linha = 0; linha < LINHAS - 1; linha++) {
        console.log(matrix_do_peso_das_distancias[linha]);
    }
}

function expandirParaOsLados() {
    var cavaleiros_que_vao_brigar = [0, 0, 0, 0, 0];

    if (DEBUG_expandirParaOsLados)
        console.log("~~~~ expandirParaOsLados ~~~~");

    if (folhas_da_arvore[0].posicao_x_da_folha > 0) {

        if (DEBUG_expandirParaOsLados)
            console.log("Expandindo para cima");

        if (matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha - 1][folhas_da_arvore[0].posicao_y_da_folha] == "P" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha - 1][folhas_da_arvore[0].posicao_y_da_folha] == "R" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha - 1][folhas_da_arvore[0].posicao_y_da_folha] == "M" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha - 1][folhas_da_arvore[0].posicao_y_da_folha] == "F" || 
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha - 1][folhas_da_arvore[0].posicao_y_da_folha] == "I") {
            criarFolha();
            copiarFolha();
            folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha--;
            folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando += valorDaLetra(folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha, folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha);
            folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento.length] = "Cima";
            //deletarCiclos();
            deletarFolhaComCavaleirosMortos();

            if (melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] > folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]) {

                if (DEBUG_expandirParaOsLados) {
                    console.log("Achou alguem com o tempo melhor");
                    console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
                }

                melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] = folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha];

                if (DEBUG_expandirParaOsLados)
                    console.log("Seu novo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
            } else {
                if (DEBUG_expandirParaOsLados) {
                    console.log("Deletando esse caminho pois existe um melhor até aquele ponto");
                    console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
                    console.log("O somatorio do que tentou entrar: " + (folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]));
                }

                folhas_da_arvore.length--;
            }
        } else {
            todasLutasPossiveisDosCavaleiros(cavaleiros_que_vao_brigar, -1, 0, "Cima");
        }

    }

    if (folhas_da_arvore[0].posicao_x_da_folha < LINHAS - 1) {

        if (DEBUG_expandirParaOsLados)
            console.log("expandindo para baixo");

        if (matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha + 1][folhas_da_arvore[0].posicao_y_da_folha] == "P" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha + 1][folhas_da_arvore[0].posicao_y_da_folha] == "R" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha + 1][folhas_da_arvore[0].posicao_y_da_folha] == "M" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha + 1][folhas_da_arvore[0].posicao_y_da_folha] == "F" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha + 1][folhas_da_arvore[0].posicao_y_da_folha] == "I") {
            criarFolha();
            copiarFolha();
            folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha++;
            folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando += valorDaLetra(folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha, folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha);
            folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento.length] = "Baixo";
            //deletarCiclos();
            deletarFolhaComCavaleirosMortos();

            if (melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] > folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]) {

                if (DEBUG_expandirParaOsLados) {
                    console.log("Achou alguem com o tempo melhor");
                    console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
                }

                melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] = folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha];

                if (DEBUG_expandirParaOsLados)
                    console.log("Seu novo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
            } else {
                if (DEBUG_expandirParaOsLados) {
                    console.log("Deletando esse caminho pois existe um melhor até aquele ponto");
                    console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
                    console.log("O somatorio do que tentou entrar: " + (folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]));
                }

                folhas_da_arvore.length--;
            }
        } else {
            todasLutasPossiveisDosCavaleiros(cavaleiros_que_vao_brigar, 1, 0, "Baixo");
        }

    }

    if (folhas_da_arvore[0].posicao_y_da_folha > 0) {

        if (DEBUG_expandirParaOsLados)
            console.log("expandindo para esquerda");

        if (matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha - 1] == "P" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha - 1] == "R" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha - 1] == "M" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha - 1] == "F" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha - 1] == "I") {
            criarFolha();
            copiarFolha();
            folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha--;
            folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando += valorDaLetra(folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha, folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha);
            folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento.length] = "Esquerda";
            //deletarCiclos();
            deletarFolhaComCavaleirosMortos();

            if (melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] > folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]) {

                if (DEBUG_expandirParaOsLados) {
                    console.log("Achou alguem com o tempo melhor");
                    console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
                }

                melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] = folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha];

                if (DEBUG_expandirParaOsLados)
                    console.log("Seu novo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
            } else {
                if (DEBUG_expandirParaOsLados) {
                    console.log("Deletando esse caminho pois existe um melhor até aquele ponto");
                    console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
                    console.log("O somatorio do que tentou entrar: " + (folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]));
                }

                folhas_da_arvore.length--;
            }
        } else {
            todasLutasPossiveisDosCavaleiros(cavaleiros_que_vao_brigar, 0, -1, "Esquerda");
        }

    }

    if (folhas_da_arvore[0].posicao_y_da_folha < COLUNAS - 1) {

        if (DEBUG_expandirParaOsLados)
            console.log("expandindo para direita");
        
        if (matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha + 1] == "P" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha + 1] == "R" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha + 1] == "M" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha + 1] == "F" ||
                matrix_do_mapa[folhas_da_arvore[0].posicao_x_da_folha][folhas_da_arvore[0].posicao_y_da_folha + 1] == "I") {
            criarFolha();
            copiarFolha();
            folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha++;
            folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando += valorDaLetra(folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha, folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha);
            folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento[folhas_da_arvore[folhas_da_arvore.length - 1].ultimo_movimento.length] = "Direita";
            //deletarCiclos();
            deletarFolhaComCavaleirosMortos();

            if (melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] > folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]) {

                if (DEBUG_expandirParaOsLados) {
                    console.log("Achou alguem com o tempo melhor");
                    console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
                }

                melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha] = folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha];

                if (DEBUG_expandirParaOsLados)
                    console.log("Seu novo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
            } else {
                if (DEBUG_expandirParaOsLados) {
                    console.log("Deletando esse caminho pois existe um melhor até aquele ponto");
                    console.log("Seu antigo melhor somatorio para essa posicao: " + melhor_tempo_nesse_lugar[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]);
                    console.log("O somatorio do que tentou entrar: " + (folhas_da_arvore[folhas_da_arvore.length - 1].tempo_caminhando + matrix_do_peso_das_distancias[folhas_da_arvore[folhas_da_arvore.length - 1].posicao_x_da_folha][folhas_da_arvore[folhas_da_arvore.length - 1].posicao_y_da_folha]));
                }

                folhas_da_arvore.length--;
            }
        } else {
            todasLutasPossiveisDosCavaleiros(cavaleiros_que_vao_brigar, 0, 1, "Direita");
        }

    }

    //deletarClones();
    deletarFolha();
    folhas_da_arvore.sort(
        function (a, b) {
            return (a.tempo_caminhando + matrix_do_peso_das_distancias[a.posicao_x_da_folha][a.posicao_y_da_folha]) - (b.tempo_caminhando + matrix_do_peso_das_distancias[b.posicao_x_da_folha][b.posicao_y_da_folha]);
        });
    atualizarMapaNaTela();

    if (DEBUG_expandirParaOsLados) {
        mostrarNoConsoleTodasAsFolhas();
        console.log("^^^^ expandirParaOsLados ^^^^");
    }
}

function f() {

    if (DEBUG_f)
        console.log("~~~~ f ~~~~");

    if (mapa_ja_criado == false) {
        alert("Primeiro precisa criar o mapa");
        if (DEBUG_f)
            console.log("^^^^ f ^^^^");
        return;
    }

    expandirParaOsLados();

    if (folhas_da_arvore[0].posicao_x_da_folha == posicao_x_do_fim && folhas_da_arvore[0].posicao_y_da_folha == posicao_y_do_fim) {
        mostraCaminho();
    } else {
        setTimeout( function () { f() }, 0);
    }

    if (DEBUG_f)
        console.log("^^^^ f ^^^^");

    return true;
}