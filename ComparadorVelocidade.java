import java.util.Comparator;


public class ComparadorVelocidade implements Comparator<Lutador> {
    @Override
    public int compare(Lutador l1, Lutador l2) {
        return l2.getVelocidade() - l1.getVelocidade();
    }
}
