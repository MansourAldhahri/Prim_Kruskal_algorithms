
class MinHeap {
  public Edgee[] heap;
  private int size;

  public MinHeap(int capacity) {
    heap = new Edgee[capacity];
    this.size = 0;
  }

  public void insertion(Edgee val) {
        this.size++;
        this.heap[this.size - 1] = val;
        this.del(this.size - 1, val);
    }

  public Edgee MinExtraction() {

        Edgee min = this.heap[0];
        this.heap[0] = this.heap[this.size - 1];

        this.size--;
        this.minHeapify(0);

        return min;
    }

   public void del(int index, Edgee newValue) {

        this.heap[index] = newValue;
        while (index > 0 && this.heap[index].weight < this.heap[this.getRoot(index)].weight) {
            swapA(this.heap, index, this.getRoot(index));
            index = this.getRoot(index);
        }
    }
   private void swapA(Edgee[] array, int index1, int index2) {
        Edgee temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;

    }
   
    
    private void minHeapify(int index) {
        int smallest = index;
        int l = this.getLC(index);
        int r = this.getRC(index);

        if (l < this.size && this.heap[l].weight < this.heap[index].weight) {
            smallest = l;
        }
        if (r < this.size && this.heap[r].weight < this.heap[smallest].weight) {
            smallest = r;
        }
        if (smallest != index) {
            swapA(this.heap, smallest, index);
            this.minHeapify(smallest);
        }
    }
    private int getLC(int index) {
        return 2 * index + 1;
    }

    private int getRC(int index) {
        return 2 * index + 2;
    }

    private int getRoot(int index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }
    public boolean Empty(){
    return size == 0 ;}
    
}