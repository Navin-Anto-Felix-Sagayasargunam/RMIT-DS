package implementation;

import java.util.List;

public abstract class RmitMultiset
{

	public static final int searchFailed = -1;

	public abstract void add(String elem);

	public abstract int search(String elem);

	public abstract List<String> searchByInstance(int instanceCount);

	public abstract boolean contains(String elem);

	public abstract void removeOne(String elem);

	public abstract String print();

	public abstract String printRange(String lower, String upper);

	public abstract RmitMultiset union(RmitMultiset other);

	public abstract RmitMultiset intersect(RmitMultiset other);

	public abstract RmitMultiset difference(RmitMultiset other);}
