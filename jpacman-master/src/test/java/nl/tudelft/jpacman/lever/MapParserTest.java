package nl.tudelft.jpacman.lever;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.io.IOException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapParserTest {

    private MapParser mapParser;
    private final LevelFactory levelCreator = mock(LevelFactory.class);
    private final BoardFactory boardFactory = mock(BoardFactory.class);

    @BeforeEach
    void setup(){
        mapParser = new MapParser(levelCreator,boardFactory);

        //模拟出棋盘中的成分 墙和地面
        when(boardFactory.createGround()).thenReturn(mock(Square.class));
        when(boardFactory.createWall()).thenReturn(mock(Square.class));

        //模拟创建出 魔鬼和豆子  不能依赖已有的实际类
        when(levelCreator.createGhost()).thenReturn(mock(Ghost.class));
        when(levelCreator.createPellet()).thenReturn(mock(Pellet.class));
    }
    @Test
    @Order(1)
    @DisplayName("null空文件名")
    void nullFile(){
        assertThatThrownBy(()->{
            mapParser.parseMap((String)null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @Order(2)
    @DisplayName("读取不存在的文件")
    void notExistFile(){
        String filename = "/notexistmap.txt";
        assertThatThrownBy(()->{
            mapParser.parseMap(filename);
        }).isInstanceOf(PacmanConfigurationException.class)
            .hasMessage("Could not get resource for: "+filename);
    }

    @Test
    @Order(3)
    @DisplayName("存在的文件")
    void existFile() throws IOException {
        String filename = "/simplemap.txt";
        mapParser.parseMap(filename);

        verify(boardFactory,times(4)).createGround();
        verify(boardFactory,times(2)).createWall();
        verify(levelCreator,times(1)).createGhost();

    }

    @Test
    @Order(4)
    @DisplayName("不识别的地图文件")
    void unrecognizedMap(){
        String filename = "/unrecognizedcharMap.txt";
        assertThatThrownBy(()->{
            mapParser.parseMap(filename);
        }).isInstanceOf(PacmanConfigurationException.class)
            .hasMessage("Invalid character at 0,0: A");
    }

}
