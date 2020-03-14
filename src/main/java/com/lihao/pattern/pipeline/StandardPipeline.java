package com.lihao.pipeline;

public class StandardPipeline implements Pipeline {

    protected Valve head;
    protected Valve tail;

    @Override
    public Valve getHead() {
        return head;
    }

    @Override
    public Valve getTail() {
        return tail;
    }

    @Override
    public void setTail(Valve v) {
        tail = v;
    }

    @Override
    public void addValve(Valve v) {
        if(head == null){
            head = v;
            v.setNext(tail);
        }else{
            Valve current = head;
            while(current != null){
                if(current.getNext() == tail){
                    current.setNext(v);
                    v.setNext(tail);
                    break;
                }
                current = current.getNext();
            }
        }
    }
}
