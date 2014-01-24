
package com.ripple.core.serialized;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ripple.core.runtime.Value;
import com.ripple.encodings.common.B16;

/**
 * @param <T> The SerializedType class
 */
public abstract class TypeTranslator<T extends SerializedType> {

    @SuppressWarnings("unchecked")
    public T fromValue(Object object) {
        switch (Value.typeOf(object)) {
            case STRING:
                return fromString((String) object);
            case DOUBLE:
                return fromDouble((Double) object);
            case INTEGER:
                return fromInteger((Integer) object);
            case LONG:
                return fromLong((Long) object);
            case BOOLEAN:
                return fromBoolean((Boolean) object);
            case JSON_ARRAY:
                return fromJSONArray((JSONArray) object);
            case JSON_OBJECT:
                return fromJSONObject((JSONObject) object);
            case UNKNOWN:
            default:
                return (T) object;
        }
    }

    public JSONObject toJSONObject(T obj) {
        throw new UnsupportedOperationException();
    }

    public JSONArray toJSONArray(T obj) {
        throw new UnsupportedOperationException();
    }

    public boolean toBoolean(T obj) {
        throw new UnsupportedOperationException();
    }

    public long toLong(T obj) {
        throw new UnsupportedOperationException();
    }

    public int toInteger(T obj) {
        throw new UnsupportedOperationException();
    }

    public double toDouble(T obj) {
        throw new UnsupportedOperationException();
    }

    public String toString(T obj) {
        throw new UnsupportedOperationException();
    }

    public T fromJSONObject(JSONObject jsonObject) {
        throw new UnsupportedOperationException();
    }

    public T fromJSONArray(JSONArray jsonArray) {
        throw new UnsupportedOperationException();
    }

    public T fromBoolean(boolean aBoolean) {
        throw new UnsupportedOperationException();
    }

    public T fromLong(long aLong) {
        throw new UnsupportedOperationException();
    }

    public T fromInteger(int integer) {
        throw new UnsupportedOperationException();
    }

    public T fromDouble(double aDouble) {
        throw new UnsupportedOperationException();
    }

    public T fromString(String value) {
        throw new UnsupportedOperationException();
    }

    public abstract Object toJSON(T obj);

    public abstract void toBytesList(T obj, BytesList to);

    /**
     * @param hint Using a boxed integer, allowing null for no hint
     *             This generic parameter can be used to hint the amount of
     *             bytes (VL) or amount of fields (STObject) for example
     */
    public abstract T fromParser(BinaryParser parser, Integer hint);

    public T fromParser(BinaryParser parser) {
        return fromParser(parser, null);
    }

    public byte[] toWireBytes(T obj) {
        BytesList to = new BytesList();
        toBytesList(obj, to);
        return to.bytes();
    }

    public T fromWireBytes(byte[] b) {
        return fromParser(new BinaryParser(b));
    }

    public String toWireHex(T obj) {
        BytesList to = new BytesList();
        toBytesList(obj, to);
        return to.bytesHex();
    }

    public T fromWireHex(String hex) {
        return fromWireBytes(B16.decode(hex));
    }
}
