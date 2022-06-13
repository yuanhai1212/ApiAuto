import java.util.Arrays;

class MyArrayList{
    private int n=3;
    private int date[];
    private int  count=0;

    public MyArrayList(){
        date=new int[n];
    }

    public void add(int element){
        if(count==n){
            int newDate[] =new int[n*2];
            for (int i = 0; i <n ; i++) {
                newDate[i]=date[i];
            }
            n*=2;
            date=newDate;
        }
        date[count]=element;
        count++;
    }

    public int[] getDate(){
        return this.date;
    }

}

class LinkList{
    public class Node{
        private Node next;
        private int date;
        public Node(int date,Node next){
            this.date=date;
            this.next=next;
        }
    }
    public Node header=null;
    public Node tail=null;



    public void travel(){
        Node p =header;
        while(p!=null){
            System.out.println(p.date);
            p=p.next;
        }
    };
    public Node find(int date){
        Node p=header;
        while(p !=null){
            if(p.date==date) return p;
            p=p.next;
        }
        return null;
    };
    public void insertatheader(int value){
        Node newNode=new Node(value,null);
        newNode.next=header;
        header=newNode;
    }

    public void inserttail(int value){
        Node newNode=new Node(value,null);
        if(header==null){
            header=newNode;
            tail=newNode;
        }else{
            tail.next=newNode;
            tail=newNode;
        }
    }
    public void delete(){};
}

public class test {
    public static void main(String[] args) {
        LinkList linkList=new LinkList();
        linkList.insertatheader(1);
        linkList.insertatheader(2);
        linkList.insertatheader(3);
        linkList.travel();
    }
}
