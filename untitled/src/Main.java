public class Main {
    public static void main(String[] args) {
        RBTree<String> drzewo = new RBTree<String>();

        drzewo.add(50, "tak");
        drzewo.add(20, "nie");
        drzewo.add(10, "nie wiem");
        drzewo.add(12, "123");
        drzewo.add(62, "Tak");
        drzewo.add(87, "ak");
        drzewo.add(86, "t");
        drzewo.add(8, "t");
        drzewo.add(85, "t");
        drzewo.add(84, "t");
        drzewo.add(9, "witamSerdecznie");
        drzewo.wypisz();

        drzewo.height();

        drzewo.delete(50);

        drzewo.wypisz();

        drzewo.get(11);
    }
}