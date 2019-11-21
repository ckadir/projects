package odev4;

public class search {

    list path = new list();
    int board; // tahta boyutu 
    int move_c[] = {2, 2, -2, -2, 1, 1, -1, -1}; // atın hareketleri
    int move_r[] = {-1, 1, 1, -1, 2, -2, 2, -2};

    /**
     *
     * atın mevcut konumuna atın hareketleri eklenir ve bu fonksiyon ile
     * atın yeni olacak olan konumu tahta içerisinde mi kontrol eder
     *
     * @param x atın x deki koordinatı
     * @param y atın y deki koordinatı
     * @return
     */
    public boolean isEdge(int x, int y) {
        boolean is = (x < board && y < board && y > 0 && x > 0);
        return is;
    }

    public static void main(String[] args) {
        node konum = new node(2, 0, 0);
        node hedef = new node(0, 2, 0);
        search s = new search(konum, hedef, 8); // 3. parametre tahtanın boyutu 8x8
    }

    /**
     * Breadth first search algoritmasına göre çalışır.Bu algoritma ağaçtaki
     * dalları aynı katmandaki düğümleri tarayarak devam eder. bizim veri
     * yapımızda ağaç yok.Burada atın mevcut konumundaki hareketleri
     * taranır.Örneğin at mevcut konumunda 3 farklı kareye gidebiliyorsa bu üç
     * kare kuyruğa eklenir daha sonra bu üç kareninde çocukları taranır.daha
     * sonra bir alt katmana geçilir.daha sonra tekrar çocukları taranır soy
     * ağacı gibi bir yapı ortaya çıkar.
     *
     * @param n1 atın mevcut konumu
     * @param n2 atın gitmesi istenen konumu
     * @param board tahtanın boyutu 8x8 için 8 girilir
     */
    public search(node n1, node n2, int board) {
        this.board = board;
        queue q = new queue();
        q.add(n1.x, n1.y, 0);
        int tx, ty;
        boolean loop = true;
        while (loop) {
            node tmp = q.get(); // kuyruktan eleman çıkartılır 
            
            for (int i = 0; i < 8; i++) { // bu for atın hareketlerini tarar ve isEdge ile
                tx = tmp.x; // kuyruktan çıkan elemanın x ve değerleri 
                ty = tmp.y;
                tx += move_r[i]; // atın hareketleri konuma ekleniyor
                ty += move_c[i];
                if (isEdge(tx, ty)) { // bölge içinde mi
                    q.add(tx, ty, (tmp.d + 1)); // 3. parametre atın hareket sayısını ifade eder
                    q.addParent(tmp);
                }
                if (tx == n2.x && ty == n2.y) {
                    System.out.println("UZAKLIK : " + (tmp.d + 1));
                    path.add(tx, ty);

                    while (tmp != null && tmp.next != null) {
                        path.add(tmp.x, tmp.y);
                        tmp = tmp.parent;
                       
                    }
                     path.add(tmp.x, tmp.y);
                    path.show();
                    loop = false;
                }
            }
        }
    }
}

class list {

    node2 top;

    public void add(int x, int y) {
        node2 n = new node2(x, y);
        if (top == null) {
            top = n;
        } else {
            n.next = top;
            top = n;
        }

    }

    public node2 get() {
        if (isNull(top)) {
            return null;
        }
        node2 n = top;
        top = top.next;
        return n;
    }

    public void show() {
        node2 tmp = top;
        while (tmp != null) {
            System.out.println("X = " + tmp.x + " | Y = " + tmp.y);
            tmp = tmp.next;
        }
    }

    public boolean isNull(node2 n) {
        return (n == null);
    }

}

class node2 {

    int x, y;
    node2 next;

    public node2(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

/**
 * standart kuyruk yapısı
 *
 * @author ckadir
 */
class queue {

    node top;

    public void addParent(node parent) {

        node tmp = top;
        while (!isNull(tmp.next)) {
            tmp = tmp.next;
        }
        tmp.parent = parent;
    }

    public void add(int x, int y, int d) {
        node n = new node(x, y, d);
        if (isNull(top)) {
            top = n;
        } else {
            node tmp = top;
            while (!isNull(tmp.next)) {
                tmp = tmp.next;
            }
            tmp.next = n;
        }
    }

    public node get() {
        if (isNull(top)) {
            return null;
        }
        node n = top;
        top = top.next;
        return n;
    }

    public boolean isNull(node n) {
        return (n == null);
    }
}

class node {

    node next;
    node parent;
    int x, y, d = 0;

    public node(int x, int y, int d) {
        next = null;
        this.x = x;
        this.y = y;
        this.d = d;
    }
}