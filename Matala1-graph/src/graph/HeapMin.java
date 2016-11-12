package graph;
public class HeapMin {

	double _positiveInfinity = Double.POSITIVE_INFINITY;
	final int INIT_SIZE = 10;
	private Vertex _a[];
	private int _size;
	public HeapMin(Vertex arr[]){
		_size = arr.length;
		_a = new Vertex[_size];
		for (int i=0; i<_size; i++){
			_a[i]=arr[i];
		}
	}
	public HeapMin(){
		_a = new Vertex[0];
	}
	/** returns the heap size*/
	public int getSize(){return _size;}
	/** returns the heap array */
	public Vertex[] getA(){ return _a;}
	
	/** parent returns the parent of name  i*/
	private  int parent(int i){return (i-1)/2;}
	
	/** leftChild returns the left child of name  i*/
	private  int leftChild(int i){return 2*i+1;}
	/** rightChild returns the right child of name  i*/
	private  int rightChild(int i){return 2*i+2;}
	/** returns the heap minimum */
	public Vertex heapMinimum(){return _a[0];}
	/** returns true if the heap is empty, otherwise false */
	public boolean isEmpty(){
		boolean ans = false;
		if (_size == 0) ans = true;
		return ans;
	}
	/** the minHeapfy function maintains the min-heap property */
	private void minHeapify(int v, int heapSize){
		int smallest;
		int left = leftChild(v);
		int right = rightChild(v);
		if (left<heapSize && _a[left].dist<_a[v].dist){
			smallest = left;
		}
		else{
			smallest = v;
		}
		if (right<heapSize && _a[right].dist<_a[smallest].dist){
			smallest = right;
		}
		if (smallest!=v){
			exchange(v, smallest);
			minHeapify(smallest, heapSize);
		}		
	}
	/** the heap minimum element extraction */
	public Vertex heapExtractMin(){
		double min = _positiveInfinity; 
		Vertex v=null;
		if (!isEmpty()){
			v = _a[0];
			min = v.dist;
			_a[0]=_a[_size-1];
			_size = _size-1;
			minHeapify(0, _size);
		}
		return v;
	}
	/** the heapDecreaseKey implements the Decrease Key operation*/
	public void heapDecreaseKey(Vertex node){
		int v = node.name;
		int i = 0;
		while (i<_size && v!=_a[i].name) i++;
		if (node.dist <_a[i].dist){
			_a[i] = node;
			while (i>0 && _a[parent(i)].dist>_a[i].dist){
				exchange(i, parent(i));
				i = parent(i);
			}
		}
	}
	/** minHeapInsert function implements the Insert-Key operation*/
	public void minHeapInsert(Vertex node){
		resize(1);
		_a[_size-1] = new Vertex(node);
		_a[_size-1].dist = _positiveInfinity;
		heapDecreaseKey(node);
	}
	/** increment an array*/
	private void resize(int increment){
		Vertex temp[] = new Vertex[_size+increment];
		for (int i=0; i<_size; i++){
			temp[i]=_a[i];
		}
		_a = temp;
		_size = _size+increment;
	}	
	/** exchange two array elements*/
	private void exchange(int i, int j){
		Vertex t = _a[i];
		_a[i] = _a[j];
		_a[j] = t;
	}
/*	*//** print a heap array **//*
	public void print(){
		for (int i=0; i<_size; i++){
			System.out.print(_a[i]+"; ");
		}
		System.out.println();
	}*/
	public boolean contains(int name){
		boolean ans = false;
		for (int i=0; !ans && i<_size; i++){
			if (_a[i].name == name) ans = true;
		}
		return ans;
	}
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vertex nodes1[] = {new Vertex(16,0),
						 new Vertex(5,1),
						 new Vertex(8,2),
						 new Vertex(4,3),
						 new Vertex(2,4),
						 new Vertex(1,5)}; 
		//int nodes1[] = {16,8,4,2,1}; 
		//int nodes2[] = {5,13,2,25,7,17,2,8,4}; 
		//int nodes3[] = {16,14,10,8,7,9,3,2,4,1}; 
		HeapMin hp = new HeapMin(nodes1);
		hp.buildMinHeap();
		hp.print();
		Vertex node = new Vertex(3,nodes1.length);
		hp.minHeapInsert(node);
		hp.print();
		System.out.println("min = "+ hp.heapExtractMin());
		hp.print();
		System.out.println("min = "+ hp.heapExtractMin());
		hp.print();
/// heap sort
		
		HeapMin hps = new HeapMin(nodes1);
		hps.heapSort();
		hps.print();
	}
*/
}
