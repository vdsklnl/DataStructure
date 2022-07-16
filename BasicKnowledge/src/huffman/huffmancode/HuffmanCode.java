package huffman.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-06-09 10:55
 * @Description
 */

public class HuffmanCode {
    public static void main(String[] args) {
        /*
         * 测试赫夫曼编码/解码字符串
         */
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        byte[] zip = huffmanZip(contentBytes);
////        System.out.println(Arrays.toString(zip));
//        byte[] decode = decode(huffmanCodes, zip);
//        System.out.println(zip.length);
//        System.out.println(new String(decode));

        /*
         * 测试压缩文件
         */
        //文件不能太大，超过数组容量限制
//        String srcFile = "BasicKnowledge\\src\\src.jpg";
//        String dstFile = "BasicKnowledge\\src\\dst.zip";
//        zipFile(srcFile, dstFile);

        /*
         * 测试还原文件
         */
//        String srcFile = "BasicKnowledge\\src\\dst.zip";
//        String dstFile = "BasicKnowledge\\src\\src1.jpg";
//        unzipFile(srcFile, dstFile);

    }

    //解压文件
    public static void unzipFile(String srcFile, String dstFile) {
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            //获取输入输出流
            ois = new ObjectInputStream(new FileInputStream(srcFile));
            fos = new FileOutputStream(dstFile);
            //对象流存储，对应读取即可，先数组后编码表
            byte[] bytes = (byte[]) ois.readObject();
            Map<Byte, String> huffCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] decode = decode(huffCodes, bytes);
            fos.write(decode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //压缩文件
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream fis = null;
        ObjectOutputStream oos = null;
        try {
            //获取输入流与输出流
            fis = new FileInputStream(srcFile);
            oos = new ObjectOutputStream(new FileOutputStream(dstFile));

            //生成byte[]数组
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            //根据bytes生成赫夫曼编码数组，此时huffmanCodes中记录对应编码表
            byte[] zip = huffmanZip(bytes);

            //对象流可以直接写入整个对象，将数组和编码表均写入，否则无法解码
            oos.writeObject(zip);
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    //解码
    //返回编码前原数组，赫夫曼编码表用huffmanCodes存储，不需传入
    private static byte[] decode(Map<Byte, String> map, byte[] huffmanBytes) {
        //先得到数组对应二进制字符串
        StringBuilder builder = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < huffmanBytes.length; i++) {
            if (i == huffmanBytes.length - 1)
                flag = false;
            builder.append(byteToBitString(flag, huffmanBytes[i]));
        }

        //反转编码表，将huffmanCodes键值对反过来
        Map<String, Byte> deHuffmanCodes = new HashMap<>();
        for (Map.Entry<Byte, String> entry:map.entrySet()) {
            deHuffmanCodes.put(entry.getValue(), entry.getKey());
        }

        //创建存放字节集合
        List<Byte> list = new ArrayList<>();
        //遍历builder，识别到字符就放入集合
        for (int i = 0; i < builder.length(); ) {
            int count = 1;
            boolean loop = true;
            Byte b = null;
            //让i不动，count++来寻找字节，当找到时退出
            while (loop) {
                //获取字符串，来寻找字节
                String substring = builder.substring(i, i + count);
                b = deHuffmanCodes.get(substring);
                if (b == null) //未找到
                    count++;
                else  //找到则退出
                    loop = flag;
            }
            //此时b中记录对应字节
            list.add(b);
            //将i移动到字符串末尾，长度为count
            i += count;
        }
        //结束后list已经存入所有字节

        byte[] deBytes = new byte[list.size()];
        for (int i = 0; i < deBytes.length; i++) {
            deBytes[i] = list.get(i);
        }
        return deBytes;
    }

    //将一个byte转化为二进制字符串
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b; //保存传入byte
        //首先进行判断，如果是正数需要进行补位操作，最后一个字节无需补高位
        if (flag)
            temp |= 256; //位与256即可完成，负数不变，正数补位
        //返回temp对应二进制补码，长度为int类型，需要截取后八位作为byte
        String str = Integer.toBinaryString(temp);
        if (flag)
            return str.substring(str.length() - 8);
        else
            return str;
    }

    //编码
    //将整体封装成一个方法，获得压缩后的字节数组
    private static byte[] huffmanZip(byte[] bytes) {
        //将字符数组转化为节点集合
        List<Node> nodes = getNodes(bytes);
        //生成赫夫曼树
        Node huffmanTree = getHuffmanTree(nodes);
        //前序遍历
//        preOrder(huffmanTree);
        //测试生成赫夫曼编码
//        Map<Byte, String> codes = getCodes(huffmanTree);
//        System.out.println(codes);
        getCodes(huffmanTree);
        //根据赫夫曼编码生成二进制字符串，并转化为字节进行存储
        byte[] zip = zip(bytes);
        return zip;
    }

    //根据赫夫曼编码生成二进制字符串，并转化为字节进行存储(补码-1 => 反码 取反 =>原码)
    private static byte[] zip(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b:bytes) {
            builder.append(huffmanCodes.get(b));
        }
        //每八位进行存储，多余新增一位存储，新数组长度
        int len = (builder.length() + 7) / 8;
        byte[] huffmanBytes = new byte[len];
        int index = 0; //新数组索引
        for (int i = 0; i < builder.length(); i += 8) {
            if ((i + 8) > builder.length()) //将剩余直接存储
                //先获得后面字串转化为int类型，再转为byte类型存储进新数组
                huffmanBytes[index] = (byte) Integer.parseInt(builder.substring(i), 2);
            else  //其余情况每8位转化存储
                huffmanBytes[index] = (byte) Integer.parseInt(builder.substring(i, i + 8), 2);
            index++;
        }
        return huffmanBytes;
    }

    /*
     * 生成赫夫曼树对应赫夫曼编码
     * 存放在Map<Byte, String>中，形如：32 -> 01 此类
     * 需要一个StringBuilder去拼接叶子节点路径
     * 左子节点路径为0，右子节点路径为1
     */
    private static Map<Byte, String> huffmanCodes = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();

    //调用方便，重载
    private static Map<Byte, String> getCodes(Node node) {
        if (node == null)
            return null;
        else
            getCodes(node, "", stringBuilder);
        return huffmanCodes;
    }

    private static void getCodes(Node node, String str, StringBuilder stringBuilder) {
        //将已有stringBuilder内字符串传给下一个，并添加新的str
        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(str);
        //node = null 不用处理
        if (node != null) {
            //判断此时节点是否为叶子节点(只有叶子节点才有对应data)
            if (node.data == null) {
                //递归处理
                getCodes(node.getLeft(), "0", builder);
                getCodes(node.getRight(), "1", builder);
            } else //找到叶子节点，且路径已被记录完成，存储
                huffmanCodes.put(node.data, builder.toString());
        }
    }


    //接受字节数组并返回相应Node集合
    private static List<Node> getNodes(byte[] bytes) {
        //使用Map来存放键值对，进行统计
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b:bytes) {
            Integer count = map.get(b);
            if (count == null) //首次添加，没有字符
                map.put(b, 1);
            else 
                map.put(b, count + 1);
        }
        
        //存放完成后，将Map中键值对(存储形式为Entry)转化为Node并放入集合
        List<Node> list = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry:map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    private static void preOrder(Node node) {
        node.preOrder();
    }

    //通过集合创建新的赫夫曼树
    private static Node getHuffmanTree(List<Node> nodes) {
        //辅助节点三个
        Node left = null;
        Node right = null;
        Node parent = null;

        //循环生成
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //生成节点，并进行连接
            left = nodes.get(0);
            right = nodes.get(1);
            parent = new Node(null, left.weight + right.weight);
            parent.setLeft(left);
            parent.setRight(right);
            //删除并新建
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    public Byte data; //存放字符本身，以ASCII码 如'a' -> 97
    public int weight; //权值，表示字符出现次数
    private Node left;
    private Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //升序排列
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }
}
