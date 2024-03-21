public class Node<T> {
    enum Kolor {
        RED,
        BLACK
    }
    int key;
    T value;
    Kolor color;
    Node rodzic;
    Node lewo;
    Node prawo;

    Node(){}
    Node(Node<T> parent){
        rodzic = parent;
    }
}
