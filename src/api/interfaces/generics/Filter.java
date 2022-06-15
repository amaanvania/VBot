package api.interfaces.generics;

public interface Filter<F>
{
    public boolean accept(F value);
}
