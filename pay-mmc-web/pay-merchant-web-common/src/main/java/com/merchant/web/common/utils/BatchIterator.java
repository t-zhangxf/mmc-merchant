package com.merchant.web.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BatchIterator<E> implements Iterator<List<E>> {
    private int batchSize;
    private List<E> srcList;
    private int index = 0;
    private List<E> result;
    private int size = 0;
    public BatchIterator(List<E> srcList, int batchSize) {
        if (0 >= batchSize) {
            throw new RuntimeException(
                    "Please do not be set less than or equal to '0' for GenericBatchIterator's batchSize !");
        }
        this.batchSize = batchSize;
        this.srcList = srcList;
        this.size = null == srcList ? 0 : srcList.size();
        result = new ArrayList<E>(batchSize);
    }
    @Override
    public boolean hasNext() {
        return index < size;
    }
    @Override
    public List<E> next() {
        result.clear();
        for (int i = 0; i < batchSize && index < size; i++) {
            result.add(srcList.get(index++));
        }
        return result;
    }
    /**
     * 暂不支持移除子集合方法
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * @desc list  split valid list
     */
    private static void listSplit() {
        List<Integer> numBox = new ArrayList<Integer>();
        Collections.addAll(numBox, 1, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27);
        Iterator<List<Integer>> batchIterator = new BatchIterator(numBox, 3);
        while (batchIterator.hasNext()) {
            List<Integer> numbers = batchIterator.next();
            System.out.println(numbers);
        }
        System.out.println("test4 end --");
    }

    public static void main(String[] args) {
        listSplit();
    }
}
