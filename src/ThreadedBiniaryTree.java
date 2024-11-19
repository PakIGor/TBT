

/*/**
          lBit rBit 
            |   |    
left node| | | | | | right node
              |
            Data
 */
 
class Node{
    int data;

    Node left;//left ptr
    Node right;//right ptr

    int lBit;//indecator, if 1 means has a child node if zero connected to a thread
    int rBit;

    Node(int data){
        this.data = data;
    }

}

public class ThreadedBiniaryTree {
    static Node root;
    static Node current;//new node
    static boolean directionRight = false;
    static boolean directionLeft = false;

    public static void createNode(int data){
        Node node = new Node(data);
        if(root.left == root && root.right == root){//if root.left equal to itself and root.right equal to itself
            node.lBit = root.lBit;
            node.left = root.left;
            root.left = node;
            root.lBit = 1;
            node.rBit = 0;
            node.right = root;
        }else{
            current = root.left;

            while(true){
                if(node.data<current.data){// left
                    if(current.lBit ==0){
                        directionLeft = true;
                        directionRight = false;
                        break;
                    } 
                    else{current = current.left;}//move to the next node
                   
                } 
                else if(current.rBit == 0)//right
                {
                    directionLeft = false;
                    directionRight = true;
                    break;
                }else{
                    current = current.right;
                }
            }
        }
        if(directionLeft){
            node.lBit = current.lBit;
            node.left = current.left;
            current.left = node;
            current.lBit = 1;
            node.rBit = 0;
            node.right = current;
        }
        else if(directionRight){
            node.rBit = current.rBit;
            node.right = current.right;
            current.right = node;
            current.rBit = 1;
            node.lBit = 0;
            node.left = current;
        }else{System.out.println("Program is a fail!");}
    }

    public static void inOrder(){
        current = root.left;
        while(current.lBit == 1){
            current = current.left;
        }
        while(current != root){
            System.out.println(" -> "+current.data);
            current = nextInOrder(current);
        }
    }
     
    public static Node nextInOrder(Node current){
        if(current.rBit ==0){
            return current.right;
        }
        current = current.right;
        while(current.lBit ==1){
            current = current.left;
        }
        return current;
    }

    public static void inOrderRecursive(Node temp)
    {
        if(temp != root){
            if(temp.lBit !=0){
                inOrderRecursive(temp.left);
                System.out.println(temp.data + " -> ");
            }
            if(temp.rBit != 0){
                inOrderRecursive(temp.right);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        // 1.Create the root node which is gonna hold the checking conditions
         root = new Node(999);
         root.lBit = 0;
         root.rBit = 1;
         root.left = root.right = root;
         // 2. Condition to add new roots!
         createNode(35);
         createNode(10);
         createNode(22);
         createNode(50);
         createNode(11);
         // 3. PreOrder, InOrder and PostOrder printing
         System.out.println("Output for recursive inOrder!");
         inOrderRecursive(root.left);
         System.out.println();

    }
}
