package com.netcracker.alexa.controlpanel.model.userpage;

public class UserPage {
    private Block [] blocks;

    public UserPage() {}

    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    static class Block {
        private String blockName;
        private String param;

        public Block(){}

        public Block(String blockName, String param){
            this.blockName = blockName;
            this.param = param;
        }

        public String getBlockName() {
            return blockName;
        }

        public void setBlockName(String blockName) {
            this.blockName = blockName;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }

}
