package iztechtube.updatehandler;

import iztechtube.dataformatter.IFormatter;
import iztechtube.storage.IStorage;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDataHandler<E, T> implements IDynamicStorage<E, T>{

    private final IFormatter<Map<E, T>> formatter;
    private final Map<E, T> dataMap;
    private final IStorage storage;

    protected AbstractDataHandler(IFormatter<Map<E, T>> formatter, IStorage storage){
        this.formatter = formatter;
        this.storage = storage;
        this.dataMap = formatter.toObject(storage.read());
    }

    public T get(E identifier) {
        return dataMap.get(identifier);
    }

    public Map<E, T> getDataMap() { return new HashMap<>(this.dataMap); }

    public void modify(E identifier, T object) {
        dataMap.put(identifier,object);
        String formattedStr = formatter.toFormat(dataMap);
        storage.save(formattedStr);
    }
}
