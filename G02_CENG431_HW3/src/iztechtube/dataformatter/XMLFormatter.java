package iztechtube.dataformatter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.basic.*;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.*;
import iztechtube.usersession.User;
import iztechtube.watchlistvideosbrowser.Watchlist;

import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

public class XMLFormatter implements IFormatter<Map<String, User>>{

    public String toFormat(Map<String, User> user){
        Users users = new Users(new ArrayList<>(user.values()));
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.omitField(Observable.class,"changed");
        xstream.omitField(Observable.class,"obs");
        xstream.alias("user", User.class);
        xstream.alias("users", Users.class);
        xstream.alias("watchlist", Watchlist.class);
        xstream.addImplicitCollection(Users.class, "users");
        StringWriter stringWriter = new StringWriter();
        xstream.marshal(users, new PrettyPrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public XStream createXStream() {
        XStream xstream = new XStream(new StaxDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out, "    ");
            }
        }) {
            // only register the converters we need; other converters generate a private access warning in the console on Java9+...
            @Override
            protected void setupConverters() {
                registerConverter(new NullConverter(), PRIORITY_VERY_HIGH);
                registerConverter(new IntConverter(), PRIORITY_NORMAL);
                registerConverter(new FloatConverter(), PRIORITY_NORMAL);
                registerConverter(new DoubleConverter(), PRIORITY_NORMAL);
                registerConverter(new LongConverter(), PRIORITY_NORMAL);
                registerConverter(new ShortConverter(), PRIORITY_NORMAL);
                registerConverter(new BooleanConverter(), PRIORITY_NORMAL);
                registerConverter(new ByteConverter(), PRIORITY_NORMAL);
                registerConverter(new StringConverter(), PRIORITY_NORMAL);
                registerConverter(new DateConverter(), PRIORITY_NORMAL);
                registerConverter(new CollectionConverter(getMapper()), PRIORITY_NORMAL);
                registerConverter((new ReflectionConverter(getMapper(), getReflectionProvider()){
                    @Override
                    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
                        Object result = new Users(new ArrayList<>());
                        result = this.doUnmarshal(result, reader, context);
                        return this.serializationMembers.callReadResolve(result);
                    }

                    @Override
                    public boolean canConvert(Class type) {
                        return type.equals(Users.class);
                    }
                }),PRIORITY_VERY_LOW);
                registerConverter((new ReflectionConverter(getMapper(), getReflectionProvider()){
                    @Override
                    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
                        Object result = new User();
                        result = this.doUnmarshal(result, reader, context);
                        return this.serializationMembers.callReadResolve(result);
                    }
                    @Override
                    public boolean canConvert(Class type) {
                        return type.equals(User.class);
                    }
                }),PRIORITY_VERY_LOW);
                registerConverter((new ReflectionConverter(getMapper(), getReflectionProvider()){
                    @Override
                    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
                        Object result = new Watchlist();
                        result = this.doUnmarshal(result, reader, context);
                        return this.serializationMembers.callReadResolve(result);
                    }
                    @Override
                    public boolean canConvert(Class type) {
                        return type.equals(Watchlist.class);
                    }
                }),PRIORITY_VERY_LOW);
            }
        };

        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(ArrayTypePermission.ARRAYS);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.addPermission(NullPermission.NULL);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.addPermission(new WildcardTypePermission(new String[]{
                User.class.getName(),Users.class.getName(),Watchlist.class.getName(),
                "java.lang.*"
        }));
        xstream.autodetectAnnotations(true);
        xstream.alias("user", User.class);
        xstream.alias("watchlist", Watchlist.class);
        xstream.alias("users", Users.class);
        xstream.addImplicitCollection(Users.class, "users");

        return xstream;
    }

    public Map<String,User> toObject(String data){
        XStream xstream = createXStream();
        Users users = (Users)xstream.fromXML(data);
        Map<String,User> userMap = new HashMap<>();
        for(User user : users.getUsers()){
            userMap.put(user.getUsername(), user);
        }
        return userMap;
    }

    private static class Users {
        private List<User> users;

        public Users(List<User> users){ this.users = users; }

        public List<User> getUsers(){ return this.users; }

    }

}