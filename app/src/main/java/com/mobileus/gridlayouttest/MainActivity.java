/* Exemplo pratico de como utilizar o Grid Layout, desenvolvido pelo Vinnícius Ferreira da Silva
* para a primeira aula do curso de Introdução ao Desenvolvimento Android, ministrada em 13/05/2016.
* Em caso de duvidas, entrem em contato com a equipe da Móbile US através do email:
* contato@mobileus.com.br
* */
package com.mobileus.gridlayouttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*As variaveis criadas no começo da classe
            * podem ser acessadas por qualquer método da classe.
            * Aqui usaremos uma variavel para acessar as informações no "visor" de nossa calculadora,
            * dessa forma podemos pegar os numeros digitados pelo usuario
            * e apresentar o resultado da conta neles.
            * Usaremos uma List de numeros e uma List de Operadores.
            * Uma List é um conjunto ordenado de informações. Neste caso usaremos uma lista para guardar
            * os valores digitados pelo usuario e outra para guardar qual operação deve ser realizada
            * entre o valor anterior e o seguinte a ser digitado.
            * */
    //Utilizamos uma CONSTANTE com o texto "0  não divide nada!",
    // para apresentar este texto no "visor" caso o usuario tenha tentado realizar uma divisão por zero,
    // que resultaria em erro no sistema.
    public static final String ZERO_NÃO_DIVIDE = "0 não divide nada!";
    //Variavel que irá guardar em tempo real todos os atributos do nosso "visor".
    private TextView txtValue;
    //Lista ordenada dos valores do tipo Inteiro inseridos para a conta.
    private List<Integer> numbers;
    //Lista ordenada dos operadores, que serão do tipo String em nosso exemplo,
    // informados pelo usuario de forma a saber que conta deve ser
    // feita com cada valor armazenado na List numbers.
    private List<String> operators;


    /* A função onCreate é nativa do Android e toda activity possui ela por padrão.
     * Aqui são realizadas todas as operações necessarias para a tela do app que irá aparecer
     * para o usuário.
     * Esta função é chamada antes da tela ser carregada e é a primeira pela qual passamos
     * quando estamos iniciando qualquer tela.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* As duas primeiras linhas são geradas por padrão pelo proprio Android Studio ao criar
         * uma nova Activty.
         * A primeira executa a função onCreate "pura", conforme foi desenvolvida pela
         * equipe de desenvolvimento que criou o Android. Uma parte obrigatória para o app funcionar,
         * mas que não precisamos ver e não devemos alterar.
         * A segunda linha é o que relaciona este arquivo Java, que contém toda a lógica de
         * nossa calculadora, com a parte visual da tela que criamos.
         * A palavra destacada em azul após "R.layout." é sempre o nome do arquivo xml que
         * representa nossa tela.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Aqui utilizamos o id que demos para nosso "visor" depois de "R.id.". Isto faz com que a
         * variavel que criamos no inicio do programa aponte para nosso EditText, permitindo que
         * possamos ler e alterar seus atributos.
         */
        txtValue = (TextView) findViewById(R.id.txtValue);

        /*Utilizamos o "new ArratList<>();" para inicializar nossas Lists vazias e dizer que ele deve
         * ser capaz de utilizar todas os métodos pré programadas para o tipo ArrayList.
         * Segurando o ctrl e clicando sobre a palavra "ArrayList", o Android Studio irá abrir o
         * arquivo que contém todos os métodos e propriedades que poderá utilizar assim.
         */
        numbers = new ArrayList<>();
        operators = new ArrayList<>();
    }

    /* Para todos os botões de digitos de nossa calculadora, temos o mesmo comportamento.
     * Ao clicar no botão ele irá chamar o método correspondente, que irá
     * adicionar o digito equivalente a ele no final do texto que esta atualmente em nosso "visor".
     * A função "append("String")" pega o texto que esta atualmente no EdtiText, adiciona o texto
     * passado entre as aspas ao final dele e apresenta na tela novamente.
     */
    public void buttonOneClicked(View view) {
        txtValue.append("1");
    }

    public void buttonTwoClicked(View view) {
        txtValue.append("2");
    }

    public void buttonThreeClicked(View view) {
        txtValue.append("3");
    }

    public void buttonFourClicked(View view) {
        txtValue.append("4");
    }

    public void buttonFiveClicked(View view) {
        txtValue.append("5");
    }

    public void buttonSixClicked(View view) {
        txtValue.append("6");
    }

    public void buttonSevenClicked(View view) {
        txtValue.append("7");
    }

    public void buttonEightClicked(View view) {
        txtValue.append("8");
    }

    public void buttonNineClicked(View view) {
        txtValue.append("9");
    }

    public void buttonZeroClicked(View view) {
        txtValue.append("0");
    }

    /*Quando clicamos no botão de "limpar", queremos que a calculadora
     * esqueça tudo que foi digitado ate o momento.
     * Isso significa limpar o "visor" e
     * esquecer os valores e operadores que foram informados ate agora.
     * Como vamos precisar limpar o "visor" e esquecer o que foi digitado em outras ocasiões,
     * não necessariamente fazendo sempre as duas coisas juntas,
     * criamos um método só para limapr a tela e um só para limpar as informações nas lists,
     * assim podemos reutilizar estes dois em outros métodos sem rpecisar ficar repetindo código.
     */
    public void buttonEraseClicked(View view) {
        clearText();
        clearCalcData();
    }

    /*
     * Os botões para as quatro operações matématicas possuem o mesmo comportamento.
     * Ao clicar em um deles ele ira chamar o método responsavel por pegar o valor que foi digitado
     * pelo usuario e a operação que deve ser realizada.
     */
    public void buttonMultiplyClicked(View view) {
        getCalcData("*");
    }

    public void buttonDivideClicked(View view) {
        getCalcData("/");
    }

    public void buttonMinusClicked(View view) {
        getCalcData("-");
    }

    public void buttonPlusClicked(View view) {
        getCalcData("+");
    }

    /*
     * Quando clicamos no botão "=", devemos fazer as contas e apresentar o resultado no "visor".
     */
    public void buttonEqualsClicked(View view) {
        /* Verifica se algum numero foi digitado antes de apertar o botão "=". (numbers não está vazia)
         * Se nenhum numero foi digitado, não tem conta para ser feita. Então o botão não vai fazer
         * nada.
         */
        if (!numbers.isEmpty()) {
            // pega o ultimo valor que estava no "visor" antes de clicar no "=" e adiciona na List de numeros.
            numbers.add(Integer.valueOf(txtValue.getText().toString()));
            // limpa o "visor"
            clearText();
            // chama o método que irá realziar a conta.
            calculate();
            // limpa a lista de numeros e de operadores para poder realizar novas contas.
            clearCalcData();
        }
    }

    /* Salva as informações atuais para realizar a conta depois.
     * Só é chamada pelos botões de operações matematicas, que dizem o operador que deve ser salvo
     * passando o parametro "newOperator". -> ex: getCalcData("+");
     */
    private void getCalcData(String newOperator) {
        // pega o ultimo valor que estava no "visor" antes de clicar no "=" e adiciona na List de numeros.
        numbers.add(Integer.valueOf(txtValue.getText().toString()));
        // adiciona o operador do botão que foi clicado na lsita de operadores.
        operators.add(newOperator);
        // limpa o texto do "visor"
        clearText();
    }

    /* Para limpar o texto do visor, basta usar a função "setText" da txtValue para dizer que \
     * o texto contido nele deve ser "" (nada.)
     */
    private void clearText() {
        txtValue.setText("");
    }

    /* Para "esquecer" os valores e operadores digitados ate então,
    * basta limpar as Lists number e operators.
    * Utilizamos o método "clear()", do ArrayList, para esvaziar a lista inteira.
    * Deve ser utilizado em cada lista que queremos limpar.
    * */
    private void clearCalcData() {
        numbers.clear();
        operators.clear();
    }

    /* Realiza o calculo de cada numero que foi inserido pelo usuario,
    * realizando as operações matématicas que foram informadas na ordem que foram inseridas.
    **/
    private void calculate() {
        Integer value;
        // Utiliza o método popNumbers() para retirar da lista o PRIMEIRO numero que o usuario digitou
        // e salvar na variavel value.
        value = popNumbers();
        /* passa o primeiro valor para o método realizeOperations, que irá realizar todas as contas
        * requisitadas e devolver o valor final na variavel value.
        * */
        value = realizeOperations(value);
        // Se a conta retornou um erro pela tentativa de dividir algo por 0, não faz nada.
        //Utilizamos "equals()" para comparar objetos qe não são numeros, no caso uma String.
        if(ZERO_NÃO_DIVIDE.equals(txtValue.getText())){
            /* substitui o valor que estiver no "visor" pelo resultado da conta.
             * Utiliza toString(), para transformar o valor de Inteiro ara String,
             * pois o EditText só aceita Strings como texto.
             * */
            txtValue.setText(value.toString());
        }
    }

    /* Enquanto houverem numeros e operadores nas listas,
    * irá realizar a conta correspondente ao operador utilizando o resultado da conta anterior
    * e o numero que esta no COMEÇO da lista numbers.
    * */
    private Integer realizeOperations(Integer value) {
        //variavel que vai guardar o operador que deve ser utilizado na conta atual.
        String op;
        //variavel que vai guardar o numero que esta no começo da lista numbers.
        Integer auxValue;
        /* Enquanto ainda houver um numero que não foi usado na conta
        * e ainda tiver um operador para saber o qual conta deve ser feita a seguir
        * irá fazer uma nova conta.
        * */
        while (!(numbers.isEmpty() && operators.isEmpty())) {
            // Utiliza o método popOperators() para retirar da lista o PRIMEIRO operador que ainda
            // está na lista operators e salvar na variavel op.
            op = popOperators();
            // Utiliza o método popNumbers() para retirar da lista o PRIMEIRO operador que ainda
            // está na lista numbers e salvar na variavel auxValue.
            auxValue = popNumbers();

            // O switch verifica qual operador esta na variavel op e realiza a conta equivalente
            switch (op) {
                case "*":
                    value = value * auxValue;
                    break;
                case "/":
                    //se tentar dividir algo por zero
                    if(auxValue == 0){
                        //apresenta a mensagem de erro no "visor"
                        txtValue.setText(ZERO_NÃO_DIVIDE);
                        //utiliza o return 0 para sair do método sem nem tentar realizar esta conta ou qualquer outra que viria depois.
                        return 0;
                    }
                    value = value / auxValue;
                    break;
                case "-":
                    value = value - auxValue;
                    break;
               case "+":
                    value = value + auxValue;
                    break;
                default:
                    break;
            }
        }
        return value;
    }

    /* Pega sempre o primeiro valor que esta no começo da lista de numeros, remove ele da lista e
    * retorna ele para a variavel que chamou este método.
    * */
    private Integer popNumbers() {
        Integer value = 0;
        if (!numbers.isEmpty()) {
            value = numbers.get(0);
            numbers.remove(0);
        }
        return value;
    }

    /* Pega sempre o primeiro operador que esta no começo da lista de operadores, remove ele da lista e
    * retorna ele para a variavel que chamou este método.
    * */
    private String popOperators() {
        String op = "";
        if (!numbers.isEmpty()) {
            op = operators.get(0);
            operators.remove(0);
        }
        return op;
    }
}
