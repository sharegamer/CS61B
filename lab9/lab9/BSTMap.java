package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p==null)
            return null;
        else if (p.key.compareTo(key)==0) {
            return p.value;
        }
        else if (p.key.compareTo(key)<0) {
            return getHelper(key,p.right);
        }
        else if (p.key.compareTo(key)>0) {
            return getHelper(key,p.left);
        }
        return null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key,root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        Node pre=p;
        int tag=0;
        if(p==null)
        {
            Node n=new Node(key,value);
            root=n;
            size++;
            return root;
        }
        while (p!=null)
        {
            if(p.key.compareTo(key)==0){
                p.value=value;
                return root;
            }
            if(p.key.compareTo(key)>0)
            {
                pre=p;
                p=p.left;
                tag=1;
            }
            else {
                pre=p;
                p = p.right;
                tag=2;
            }
        }
        p=new Node(key,value);
        if(tag==1)
            pre.left=p;
        else if (tag==2) {
            pre.right=p;
        }
        size++;
        return root;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        putHelper(key,value,root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set=new TreeSet<K>();
        keySethelper(set,root);
        return set;
    }
    private void keySethelper(Set<K> set,Node root)
    {
        if(root==null)
            return;
        set.add(root.key);
        keySethelper(set,root.left);
        keySethelper(set,root.right);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if(get(key)==null)
            return null;
        Node pre=root;
        Node p=root;
        int tag=0;
        while (p!=null)
        {
            if(p.key.compareTo(key)==0){
                break;
            }
            if(p.key.compareTo(key)>0)
            {
                pre=p;
                p=p.left;
                tag=1;
            }
            else {
                pre=p;
                p = p.right;
                tag=2;
            }
        }
        if(p==root)
        {
            V value=root.value;
            if(p.left==null && p.right==null)
                root=null;
            else if(p.right==null)
                root=root.left;
            else if (p.left==null)
            {
                root=root.right;
            }
            else
            {
                Node n=findsuccessor(p);
                root.key=n.key;
                root.value=n.value;
            }
            return value;
        }
        V value=p.value;
        if(p.left==null && p.right==null) {
            if(tag==1)
                pre.left=null;
            else
                pre.right=null;

        }
        else if(p.right==null)
        {
            if(tag==1)
                pre.left=p.left;
            else
                pre.right=p.left;
        }

        else if (p.left==null)
        {
            if(tag==1)
                pre.left=p.right;
            else
                pre.right=p.right;
        }
        else
        {
            Node n=findsuccessor(p);
            p.key=n.key;
            p.value=n.value;
        }
        return value;
    }
    private Node findsuccessor(Node node)
    {
        Node p;
        if(node.right==null)
            return null;
        p=node.right;
        while(p.left!=null)
            p=p.left;
        remove(p.key);
        return p;
    }
    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if(get(key)==value)
            return remove(key);
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
