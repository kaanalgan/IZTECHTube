package iztechtube.updatehandler;

public interface IDynamicStorage<E, T> {

    /* Get most recent version of the object having the given identifier.
    * @param identifier     Identifier object of type E of the desired object.
    * @return               Object of type T having the given identifier.*/
    public T get(E identifer);


    /* Refresh the information of the object having the given identifier.
    * @param1 identifier    Unique identifier of the object to be modified.
    * @param2 object        Object whose information will be modified.
    */
    public void modify(E identifier, T object);

}
