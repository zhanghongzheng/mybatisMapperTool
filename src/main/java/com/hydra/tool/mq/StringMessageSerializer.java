//package com.hydra.tool.mq;
//
//import kafka.serializer.Decoder;
//import kafka.serializer.Encoder;
//import kafka.serializer.StringDecoder;
//import kafka.serializer.StringEncoder;
//import kafka.utils.VerifiableProperties;
//
///**
// * Created by bear_lee on 15/6/23.
// */
//public class StringMessageSerializer implements Decoder<String>, Encoder<String> {
//    private static final Encoder<String> encoder = new StringEncoder((VerifiableProperties) null);
//    private static final Decoder<String> decoder = new StringDecoder((VerifiableProperties) null);
//
//    public byte[] toBytes(String message) {
//        return encoder.toBytes(message);
//    }
//
//    public String fromBytes(byte[] bytes) {
//        return decoder.fromBytes(bytes);
//    }
//}
//
