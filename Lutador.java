public abstract class Lutador {
    String nome;
    int vida;
    int energia;
    int forca;
    int velocidade;
    boolean defesa, vivo;

    public Lutador(String nome, int vida, int forca, int velocidade) {
        this.nome = nome;
        this.vida = vida;
        this.forca = forca;
        this.velocidade = velocidade;
        this.energia = 100;
        this.defesa = false;
        this.vivo = true;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void atacar(Lutador oponente) {
        if (oponente.defesa) {
            oponente.vida -= (forca - 3);
        } else {
            oponente.vida -= forca;
        }
        oponente.mostrarStatus();
        oponente.defesa = false;
        System.out.println(this.nome + " Usou um ataque normal em " + oponente.nome + "!");
    }

    public void especial(Lutador oponente) {
        if (this.energia == 0) {
            System.out.println("Sem energia para aplicar o especial!");
            return;
        }
        else if (oponente.defesa) {
            oponente.vida -= ((forca * 2) - 6);
            this.energia -= 40;
        } else {
            oponente.vida -= (forca * 2);
            this.energia -= 40;
        }
        if (this.energia < 0) {
            this.energia = 0;
        }
        oponente.defesa = false;
        System.out.println(this.nome + " usou um ataque especial em " + oponente.nome + "!");
    }

    public void defender() {
        // Caso a energia estiver abaixo de 100, ela irá recarregar de 15 em 15
        if (this.energia < 100) {
            this.energia += 15;
            // Impede que a energia ultrapasse o valor de 100
            if (this.energia > 100) {
                this.energia = 100;
            }
        }
        this.defesa = true;
        System.out.println(this.nome + " está se defendendo!\n ");
    };

    public void mostrarStatus() {
        if (this.vida < 0) {
            this.vida = 0;  
        }
        System.out.println("--- Status de " + this.nome + " ---");
        System.out.println("Vida: " + this.vida);
        System.out.println("Energia: " + this.energia);
        System.out.println("Defesa: " + (this.defesa? "Ativada" : "Desativada"));
        System.out.println("---------------------");
    }

    public boolean estarVivo() {
            if (this.vida == 0) {
                return this.vivo = false;
            } else {
                return this.vivo = true;
            }
    }
}