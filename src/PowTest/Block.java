package PowTest;


import java.sql.Timestamp;

/**
 * @author WYP
 * @date 2020-10-13 19:14
 */
public class Block {
    /**
     * 上一个区块hash
     */
    private String preHash;
    /**
     * 当前区块hash
     */
    private String hashCode;

    private Timestamp timestamp;
    /**
     * 网络难度系数，前导0个数
     */
    private int diff;
    /**
     * 存交易信息
     */
    private String data;
    /**
     * 区块高度
     */
    private int index;
    /**
     * 随机值
     */
    private int nonce;

    public void setNonce(int nonce) {this.nonce = nonce; }

    public int getNonce() {return nonce;}

    public void setPreHash(String preHash) {this.preHash = preHash;}

    public String getHashCode() {return hashCode; }

    public void setHashCode(String hashCode) {this.hashCode = hashCode;}

    public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}

    public int getDiff() {return diff;}

    public void setDiff(int diff) {this.diff = diff;}

    public void setData(String data) {this.data = data;}

    public int getIndex() {return index; }

    public void setIndex(int index) {this.index = index;}

    @Override
    public String toString() {
        return "{" +
                "preHash='" + preHash + '\'' +
                ", hashCode='" + hashCode + '\'' +
                ", timestamp=" + timestamp +
                ", diff=" + diff +
                ", data='" + data + '\'' +
                ", index=" + index +
                ", nonce=" + nonce +
                '}';


        }
    /**
     * 生成创世区块
     * @param data
     * @return Block
     */
    public Block generateFirstBlock(String data){
        this.preHash = "0";
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.diff = 4;
        this.data = data;
        this.index = 1;
        this.nonce = 0;
        //用sha256算一个hash
        this.hashCode = this.generationHashCodeBySha256();
        return this;
    }
    // 将信息转为hash
    public String generationHashCodeBySha256(){
        String hashData = ""+this.index+this.nonce+this.diff+this.timestamp;
        return Encryption.getSha256(hashData);
    }

    public static void main(String[] args) {
//        Block block = new Block();
//        block.generateFirstBlock("第一个区块");
//        System.out.println(block.toString());
        Block firstBlock = new Block();
        firstBlock.generateFirstBlock("第一个区块");
       System.out.println(firstBlock.toString());
        Block secondBlock = firstBlock.generateNextBlock("第二个区块",firstBlock);
        System.out.println(secondBlock.toString());
    }
    /**
     * 生成新的区块
     * @param data
     * @param oldBlock
     * @return Block
     */
    public Block generateNextBlock(String data,Block oldBlock){
        Block newBlock = new Block();
        newBlock.setTimestamp(new Timestamp(System.currentTimeMillis()));
        //规定前导0为4
        newBlock.setDiff(4);
        newBlock.setData(data);
        newBlock.setIndex(oldBlock.getIndex()+1);
        newBlock.setPreHash(oldBlock.getHashCode());
        //由矿工调整
        newBlock.setNonce(0);
        newBlock.setHashCode(PowAlgorithm.pow(newBlock.getDiff(),newBlock));
        return newBlock;
    }
}





