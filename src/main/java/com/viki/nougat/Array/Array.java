package com.viki.nougat.Array;

public class Array<E> {
    private E[] data;
    private int size;

    /**
     * 构造函数 ，传入数组容量capacity构造函数
     * @param capacity
     */
    public Array(int capacity){
        data= (E[])new Object[capacity];
        size=0;
    }

    /**
     * 无参构造，默认数组大小为10
     */
    public Array(){
        this(10);
    }

    /**
     * 获取数组中元素个数
     * @return 返回元素个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组容量
     * @return 返回数组当前容量
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 判断数组是否为0
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 在index 位插入一份新的元素
     * @param index
     * @param e
     */
    public void  add(int index,E e){
        if(index<0||index>size)
            throw new IllegalArgumentException("Add failed Require index >=0 and index <=size");

        if(size==data.length) //暂时抛出异常，后期处理扩容
            resize(2*data.length);

        for (int i = size-1; i >=index; i--)
            data[i+1]=data[i];
        data[index]=e;
        size++;
    }

    /**
     * 所有元素最后添加一个新的元素
     * @param e
     */
    public void addLast(E e){
//        if(size==data.length) //暂时抛出异常，后期处理扩容
//            throw new IllegalArgumentException("AddLast failed Array is full");
//        data[size]=e;  //data[size++]=e 与下面两句同意
//        size++;
        add(size,e);
    }

    /**
     * 所有元素前添加一个新元素
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     *获取index索引位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        if(index <0|| index>=size)
            throw new IllegalArgumentException("Get failed .Index is illegal");
        return data[index];
    }

    public E getLast(){
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }

    /**
     *修改index索引位置的元素
     * @param index
     * @return
     */
    public void set(int index,E e){
        if(index <0|| index>=size)
            throw new IllegalArgumentException("Get failed .Index is illegal");
        data[index]=e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    /**
     * 查找数组中是元素e所在的索引，如果不存在元素e，则返回-1  只返回第一个 可以加一个findAll 方法
     * @param e
     * @return
     */
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    /**
     * 删除索引index位置的元素，返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index <0|| index>=size)
            throw new IllegalArgumentException("Get failed .Index is illegal");

        E ret=data[index];
        for (int i = index+1; i < size; i++)
            data[i-1]=data[i];
        size--;
        data[size]=null; //元素如果是引用类型，remove之后，依然存在，防止垃圾回收机制无法回收，用null
        if(size==data.length/4 && data.length/2!=0)
            resize(data.length/2);
        return ret;
    }

    /**
     * 从数组中删除第一个元素 返回删除的元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素，返回删除的元素
     * @return
     */
    public E removeLast(){
        return remove(size-1);
    }

    /**
     * 从数组中删除元素e， 只删除第一个 可以加一个removeAllElement
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     *toString 方法
     * @return
     */
    @Override
    public String toString(){
        StringBuilder res =new StringBuilder();
        res.append(String.format("Array:size=%d,capacity=%d", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if(i !=size-1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }


    /**
     * 扩容数组
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData =(E[])new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i]=data[i];
        data=newData;
    }
}
