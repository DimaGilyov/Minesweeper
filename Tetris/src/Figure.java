import java.awt.*;

public enum Figure {
    I("I"),
    J("J"),
    L("L"),
    O("O"),
    S("S"),
    T("T"),
    Z("Z");

    int[][] shape;
    Color color;

    Figure(String name) {
            if (name.equals("I")){
                shape = shapes[0];
                color = colors[0];
            }
            if (name.equals("J")){
                shape = shapes[1];
                color = colors[1];
            }
            if (name.equals("L")){
                shape = shapes[2];
                color = colors[2];
            }
            if (name.equals("O")){
                shape = shapes[3];
                color = colors[3];
            }
            if (name.equals("S")){
                shape = shapes[4];
                color = colors[4];
            }
            if (name.equals("T")){
                shape = shapes[5];
                color = colors[5];
            }
            if (name.equals("Z")){
                shape = shapes[6];
                color = colors[6];
            }


    }

    int[][][] shapes = {{{1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0}},

                   {{0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0}},

                   {{1, 0, 0, 0},
                    {1, 0, 0, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0}},

                   {{1, 1, 0, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}},

                   {{1, 0, 0, 0},
                    {1, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}},

                   {{0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}},

                    {{0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {1, 0, 0, 0},
                    {0, 0, 0, 0}}};
    Color[] colors = {new Color(0x00F0F0), new Color(0x0000F0), new Color(0xF0A000), new Color(0xF0F000), new Color(0x00F000), new Color(0xA000F0), new Color(0xF00000)};

}




