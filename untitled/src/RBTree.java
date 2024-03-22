public class RBTree<T> {
    private Node<T> root;

    int maxHei = 0;
    RBTree(){
        this.root = null;
    }

    private void leftRotate(Node x) {
        Node kid = x.prawo;
        x.prawo = x.lewo;
        if (kid.lewo != null) {
            kid.lewo.rodzic = x;
            kid.rodzic = x.rodzic;
        }
        if (x.rodzic == null) {
            root = kid;
        }
        else if (x == x.rodzic.lewo) {
            x.rodzic.lewo = kid;
        }
        else {
            x.rodzic.prawo = kid;
        }
        kid.lewo = x;
        x.rodzic = kid;
    }

    private void rightRotate(Node x) {
        Node kid = x.lewo;
        x.lewo = x.prawo;
        if (kid.prawo != null) {
            kid.prawo.rodzic = x;
            kid.rodzic = x.rodzic;
        }
        if (x.rodzic == null) {
            root = kid;
        }
        else if (x == x.rodzic.prawo) {
            x.rodzic.prawo = kid;
        }
        else {
            x.rodzic.lewo = kid;
        }
        kid.prawo = x;
        x.rodzic = kid;
    }



    void add(int key, T value){
        int hei = 1;
        if (root == null){
            Node<T> newNode = new Node<T>();
            newNode.key = key;
            newNode.value = value;
            newNode.color = Node.Kolor.BLACK;
            root = newNode;
            return;
        }
        Node<T> currentNode = root;
        Node<T> newNode;

        while (true){
            if(currentNode.key < key){
                System.out.println("Przechodzenie w prawo"+currentNode.key+" "+key);
                hei++;
                if (currentNode.prawo == null){
                    currentNode.prawo = new Node(currentNode);
                    newNode = currentNode.prawo;
                    break;
                }
                currentNode = currentNode.prawo;
            }
            else if(currentNode.key > key){
                hei++;
                System.out.println("Przechodzenie w lewo " + currentNode.key + " " + key);
                if (currentNode.lewo == null){
                    currentNode.lewo = new Node(currentNode);
                    newNode = currentNode.lewo;
                    break;
                }
                currentNode = currentNode.lewo;
            }
            else{
                return;
            }
        }
        newNode.key = key;
        newNode.value = value;
        newNode.color = Node.Kolor.RED;
        System.out.println("Nowy node: "+newNode.key+" "+newNode.value);
        System.out.println("Nowy node: "+currentNode.key+" "+currentNode.value);
        System.out.println("Nowy node: "+root.lewo+" "+root.lewo);
        if(hei > maxHei){
            maxHei = hei;
        }

        while(newNode.rodzic != root){
            if(newNode.rodzic.color == Node.Kolor.RED && newNode.color == Node.Kolor.RED){
                newNode.rodzic.color = Node.Kolor.BLACK;
            }
            else if(newNode.rodzic.color == Node.Kolor.BLACK && newNode.color == Node.Kolor.BLACK){
                newNode.rodzic.color = Node.Kolor.RED;
            }
            else{break;}
            newNode = newNode.rodzic;
        }




        /*while(newNode.rodzic != root && newNode.color == Node.Kolor.RED){
//            if(newNode.rodzic.rodzic.prawo == null){
//                rightRotate(newNode.rodzic.rodzic);
//            }
//            else if (newNode.rodzic.rodzic.lewo == null) {
//                leftRotate(newNode.rodzic.rodzic);
//            }
            if(newNode.rodzic == newNode.rodzic.rodzic.lewo) {
                Node y = newNode.rodzic.rodzic.prawo;
                if (y.color == Node.Kolor.RED) {
                    newNode.rodzic.color = Node.Kolor.BLACK;
                    y.color = Node.Kolor.BLACK;
                    newNode.rodzic.rodzic.color = Node.Kolor.RED;
                    newNode = newNode.rodzic.rodzic;
                } else if (newNode == newNode.rodzic.prawo) {
                    newNode = newNode.rodzic;
                    leftRotate(newNode);
                    System.out.println("Left Rotate");
                } else {
                    newNode.rodzic.color = Node.Kolor.BLACK;
                    newNode.rodzic.rodzic.color = Node.Kolor.RED;
                    rightRotate(newNode.rodzic.rodzic);
                    System.out.println("Right Rotate");
                }
            }
            else{
                Node y = newNode.rodzic.rodzic.lewo;
                if(y.color == Node.Kolor.RED){
                    newNode.rodzic.color = Node.Kolor.BLACK;
                    y.color = Node.Kolor.BLACK;
                    newNode.rodzic.rodzic.color = Node.Kolor.RED;
                    newNode = newNode.rodzic.rodzic;
                }
                else if(newNode == newNode.rodzic.lewo) {
                    newNode = newNode.rodzic;
                    rightRotate(newNode);
                    System.out.println("Right Rotate");

                }
                else{
                    newNode.rodzic.color = Node.Kolor.BLACK;
                    newNode.rodzic.rodzic.color = Node.Kolor.RED;
                    leftRotate(newNode.rodzic.rodzic);
                    System.out.println("Left Rotate");
                }
            }
        }*/


//            if(newNode.rodzic.color == Node.Kolor.RED && newNode.color == Node.Kolor.RED){
//                newNode.rodzic.color = Node.Kolor.BLACK;
//            }
//            else if(newNode.rodzic.color == Node.Kolor.BLACK && newNode.color == Node.Kolor.BLACK){
//                newNode.rodzic.color = Node.Kolor.RED;
//            }
//            else{break;}
//            newNode = newNode.rodzic;
    }



    public void wypisz(){

        wypiszTo(root);
    }

    private void wypiszTo(Node node) {
        if (node == null) {
            return;
        }
        if (node != null) {
            System.out.print(node.key+" ");
//
//            wypiszTo(node.lewo);
//            wypiszTo(node.prawo);
//
            if (node.lewo != null) {
                System.out.print(node.lewo.key+" ");
            }
            else {System.out.print(-1+" ");}
            if (node.prawo != null) {
                System.out.print(node.prawo.key + " ");
            }
            else {System.out.print(-1+" ");}
            System.out.print(node.color+" ");
            System.out.println("");
            if (node.lewo != null) {
                wypiszTo(node.lewo);
            }
            if (node.prawo != null) {
                wypiszTo(node.prawo);
            }
        }
    }

    void delete(int key){
        Node<T> curNode = root;
        while (curNode.key != key){
            if(key < curNode.key){
                if(curNode.lewo != null){
                    curNode = curNode.lewo;
                }
                else{
                    System.out.println("Nie znaleziono elementu");
                    return;
                }
            }
            else{
                if(curNode.prawo != null){
                    curNode = curNode.prawo;
                }
                else{
                    System.out.println("Nie znaleziono elementu");
                    return;
                }
            }
        }
        System.out.println("Liczba: " + curNode.key);
        Node kid;
        if(curNode == root && root.lewo == null && root.prawo == null){
            root = null;
        }
        else{
            System.out.println("Liczba: " + curNode.key);
            kid = curNode;
            System.out.println("Liczba: " + kid.key);
            while(kid.prawo != null || kid.lewo != null){
                if(kid.prawo != null){
                    kid = kid.prawo;
                }
                else{
                    kid = kid.lewo;
                }
            }
            System.out.println("Liczba: " + kid.key);
            if(curNode != root){
                if(curNode == curNode.rodzic.prawo) {
                    curNode.rodzic.prawo = kid;
                }
                else if(curNode == curNode.rodzic.lewo) {
                    curNode.rodzic.lewo = kid;
                }
            }
            if(curNode.lewo != null){
                curNode.lewo.rodzic = kid;
                kid.lewo = curNode.lewo;
            }
            if(curNode.prawo != null){
                curNode.prawo.rodzic = kid;
                kid.prawo = curNode.prawo;
            }

            if(kid == kid.rodzic.lewo){
                kid.rodzic.lewo = null;
            }
            else{
                kid.rodzic.prawo = null;
            }
            if(curNode != root){
                kid.rodzic = curNode.rodzic;
            }
            else{
                kid.color = Node.Kolor.BLACK;
                root = kid;
            }

        }

            curNode = null;
    }



    void height(){
        System.out.println(maxHei);
    }

    T get(int key){
        Node curNode = root;
        while (curNode != null){
            if(curNode.key == key){
                System.out.println(curNode.key+" = "+curNode.value);
                return curNode.value;
            }
            else if(key < curNode.key){
                if(curNode.lewo != null){
                    curNode = curNode.lewo;
                }
                else{
                    System.out.println("Nie znaleziono elementu");
                    return null;
                }
            }
            else{
                if(curNode.prawo != null){
                    curNode = curNode.prawo;
                }
                else{
                    System.out.println("Nie znaleziono elementu");
                    return null;
                }
            }
        }
    }
}
