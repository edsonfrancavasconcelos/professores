import java.util.Scanner;

public class NotaAluno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome da escola:");
        String nomeEscola = scanner.nextLine();

        boolean continuar = true;

        do {
            System.out.println("Quantos alunos deseja inserir?");
            int numAlunos = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o próximoInt()

            String[] nomes = new String[numAlunos];
            int numMaterias;

            do {
                System.out.println("Quantas matérias deseja inserir para cada aluno?");
                numMaterias = scanner.nextInt();
            } while (numMaterias <= 0);

            String[][] professores = new String[numAlunos][numMaterias];
            double[][][] notas = new double[numAlunos][numMaterias][4]; // N matérias x 4 notas

            for (int i = 0; i < numAlunos; i++) {
                System.out.println("Digite o nome do aluno " + (i + 1) + ":");
                nomes[i] = scanner.next();
                scanner.nextLine(); // Consumir a quebra de linha após o próximoInt()

                for (int j = 0; j < numMaterias; j++) {
                    System.out.println("Digite o nome da matéria para o aluno " + nomes[i] + ":");
                    professores[i][j] = scanner.next();
                    scanner.nextLine(); // Consumir a quebra de linha após o próximoInt()

                    for (int k = 0; k < 4; k++) {
                        System.out.println("Digite a nota " + (k + 1) + " para " + professores[i][j] + ":");
                        notas[i][j][k] = scanner.nextDouble();
                    }
                }
            }

            CalculadoraMedia calculadoraMedia = new CalculadoraMedia();

            System.out.println("=== Resultados ===");
            System.out.println("Escola: " + nomeEscola);

            for (int i = 0; i < numAlunos; i++) {
                System.out.println("Disciplina e Notas do Aluno " + nomes[i] + ":");

                for (int j = 0; j < numMaterias; j++) {
                    System.out.println("  " + nomes[i] + " em " + professores[i][j] + ":");
                    double mediaMateria = calculadoraMedia.calcularMedia(notas[i][j]);
                    System.out.println("    Média em " + professores[i][j] + ": " + mediaMateria);

                    for (int k = 0; k < 4; k++) {
                        System.out.println("      Nota " + (k + 1) + ": " + notas[i][j][k]);
                    }
                }

                double mediaGeral = calculadoraMedia.calcularMedia(notas[i]);
                System.out.println("Média geral de " + nomes[i] + ": " + mediaGeral);

                if (mediaGeral >= 6) {
                    System.out.println(nomes[i] + " está aprovado(a)!");
                } else {
                    System.out.println(nomes[i] + " está reprovado(a).");
                }
            }

            System.out.println("Deseja inserir mais alunos? (Digite 'S' para continuar, 'N' para encerrar):");
            continuar = scanner.nextBoolean();
            scanner.nextLine(); // Consumir a quebra de linha após o próximoBoolean()

        } while (continuar);
    }
}

class CalculadoraMedia {
    public double calcularMedia(double[] notas) {
        if (notas.length == 0) {
            return 0;
        }

        double soma = 0;

        for (double nota : notas) {
            soma += nota;
        }

        return soma / notas.length;
    }

    public double calcularMedia(double[][] notas) {
        if (notas.length == 0 || notas[0].length == 0) {
            return 0;
        }

        double soma = 0;
        int totalNotas = 0;

        for (double[] materiaNotas : notas) {
            for (double nota : materiaNotas) {
                soma += nota;
                totalNotas++;
            }
        }

        return soma / totalNotas;
    }
}
