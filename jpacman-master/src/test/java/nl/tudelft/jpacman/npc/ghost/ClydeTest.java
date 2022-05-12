package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import nl.tudelft.jpacman.sprite.Sprite;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClydeTest {

    private static MapParser mapParser;
    private PlayerFactory playerFactory;

    @Test
    @DisplayName("Clyde离Player距离小于8个方块")
    @Order(1)
    void departLessThanEight() {
        List<String> text = Lists.newArrayList(
            "##############", "#.#....C.....P", "##############"
        );
        Level level = mapParser.parseMap(text);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.WEST);
        level.registerPlayer(player);

        Optional<Direction> opt = clyde.nextAiMove();

        assertThat(opt.get()).isEqualTo(Optional.of(Direction.EAST));
    }

    @BeforeAll
    public static void setup(){
        PacManSprites sprites = new PacManSprites();
        LevelFactory levelFactory = new LevelFactory(
            sprites,new GhostFactory(sprites),mock(PointCalculator.class)
        );
        BoardFactory boardFactory = new BoardFactory(sprites);
        GhostFactory ghostFactory = new GhostFactory(sprites);
        PacManSprites pacManSprites = new PacManSprites();
        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
        mapParser = new GhostMapParser(levelFactory,boardFactory,ghostFactory);
    }

    @Test
    @DisplayName("Clyde离Player距离大于8个方块")
    @Order(2)
    void departMoreThanEight() {
        List<String> text = Lists.newArrayList(
            "##############", "#.#....C.....P", "##############"
        );
        Level level = mapParser.parseMap(text);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        Optional<Direction> opt = clyde.nextAiMove();

        assertThat(opt.get()).isEqualTo(Optional.of(Direction.WEST));
    }

    @Test
    @DisplayName("没有Player，只有Clyde")
    @Order(3)
    void testNoPlayer() {
        Level level = mapParser.parseMap(
            Lists.newArrayList("#####", "##C  ", "     ")
        );
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        assertThat(clyde.nextAiMove()).isEqualTo(Optional.empty());
    }


    @Test
    @DisplayName("只有Player，没有Clyde")
    @Order(4)
    void testNoPathBetweenPlayerAndClyde() {
        Level level = mapParser.parseMap(
            Lists.newArrayList("######", "#P##C ", " ###  ")
        );
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        assertThat(clyde.nextAiMove()).isEqualTo(Optional.empty());
    }
}
