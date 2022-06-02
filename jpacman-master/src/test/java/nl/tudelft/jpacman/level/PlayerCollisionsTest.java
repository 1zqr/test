package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerCollisionsTest{

    private PointCalculator pointCalculator;        //分数计分器
    private Player player;                          //玩家
    private Pellet pellet;                          //豆子
    private Ghost ghost;                            //幽灵
    private CollisionMap Collisions;                //冲突处理

    public void setPointCalculator(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
    }

    public void setPlayer(Player player) {this.player = player;}

    public void setPellet(Pellet pellet) {
        this.pellet = pellet;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    public void setPlayerCollisions(CollisionMap Collisions) {
        this.Collisions = Collisions;
    }

    public PointCalculator getPointCalculator() {
        return pointCalculator;
    }


    @BeforeEach
    void init() {
        this.setPointCalculator(Mockito.mock(PointCalculator.class));
        this.setPlayer(Mockito.mock(Player.class));
        this.setPellet(Mockito.mock(Pellet.class));
        this.setGhost(Mockito.mock(Ghost.class));

        this.setPlayerCollisions(new PlayerCollisions(this.getPointCalculator()));
    }


    /**
     * 测试豆子与玩家之间的碰撞--吃掉+计分
     */
    @Order(1)
    @Test
    @DisplayName(value="测试豆子与玩家之间的碰撞--吃掉+计分")
    void testPlayerPellet() {
        Collisions.collide(player, pellet);

        Mockito.verify(pointCalculator, Mockito.times(1)).consumedAPellet(
            Mockito.eq(player),
            Mockito.eq(pellet)
        );

        Mockito.verify(pellet, Mockito.times(1)).leaveSquare();


        Mockito.verifyNoMoreInteractions(player, pellet);
    }

    /**
     * 测试豆子与玩家之间的碰撞--吃掉+计分
     */
    @Order(2)
    @DisplayName(value="测试豆子与玩家之间的碰撞--吃掉+计分")
    @Test
    void testPelletPlayer() {
        Collisions.collide(pellet, player);

        Mockito.verify(pointCalculator, Mockito.times(1)).consumedAPellet(
            Mockito.eq(player),
            Mockito.eq(pellet)
        );

        Mockito.verify(pellet, Mockito.times(1)).leaveSquare();

        Mockito.verifyNoMoreInteractions(pellet, player);
    }

    /**
     * 测试玩家与幽灵之间的碰撞--死亡
     */
    @Order(3)
    @DisplayName(value="测试玩家与幽灵之间的碰撞--死亡")
    @Test
    void testPlayerGhost() {
        Collisions.collide(player, ghost);

        Mockito.verify(pointCalculator, Mockito.times(1)).collidedWithAGhost(
            Mockito.eq(player),
            Mockito.eq(ghost)
        );

        Mockito.verify(player, Mockito.times(1)).setAlive(false);

        Mockito.verify(player, Mockito.times(1)).setKiller(Mockito.eq(ghost));

        Mockito.verifyNoMoreInteractions(player, ghost);
    }


    /**
     * 测试玩家与幽灵之间的碰撞--死亡
     */
    @Order(4)
    @DisplayName(value="测试玩家与幽灵之间的碰撞--死亡")
    @Test
    void testGhostPlayer() {
        Collisions.collide(ghost, player);

        Mockito.verify(pointCalculator, Mockito.times(1)).collidedWithAGhost(
            Mockito.eq(player),
            Mockito.eq(ghost)
        );

        Mockito.verify(player, Mockito.times(1)).setAlive(false);

        Mockito.verify(player, Mockito.times(1)).setKiller(Mockito.eq(ghost));

        Mockito.verifyNoMoreInteractions(player, ghost);
    }


    /**
     * 测试幽灵与豆子之间的碰撞--什么都不发生
     */
    @Order(5)
    @DisplayName(value="测试幽灵与豆子之间的碰撞--什么都不发生")
    @Test
    void testGhostPellet() {
        Collisions.collide(ghost, pellet);

        Mockito.verifyZeroInteractions(ghost, pellet);
    }

    /**
     * 测试豆子与幽灵之间的碰撞--什么都不发生
     */
    @Order(6)
    @DisplayName(value="测试豆子与幽灵之间的碰撞--什么都不发生")
    @Test
    void testPelletGhost() {
        Collisions.collide(pellet, ghost);

        Mockito.verifyZeroInteractions(pellet, ghost);
    }

    /**
     * 测试两个幽灵之间的碰撞--什么都不发生
     */
    @Order(7)
    @DisplayName(value="测试两个幽灵之间的碰撞--什么都不发生")
    @Test
    void testGhostGhost() {
        Ghost ghost1 = Mockito.mock(Ghost.class);
        Collisions.collide(ghost, ghost1);

        Mockito.verifyZeroInteractions(ghost, ghost1);
    }


    /**
     * 测试豆子与豆子之间的碰撞--什么都不发生
     */
    @Order(8)
    @DisplayName(value="测试豆子与玩家之间的碰撞--吃掉+计分")
    @Test
    void testPelletPellet() {
        Pellet pellet1 = Mockito.mock(Pellet.class);
        Collisions.collide(pellet, pellet1);

        Mockito.verifyZeroInteractions(pellet, pellet1);
    }


    /**
     * 测试两个玩家之间的碰撞--什么都不发生
     */
    @Order(9)
    @DisplayName(value="测试豆子与玩家之间的碰撞--吃掉+计分")
    @Test
    void testTwoPlayer() {
        Player player1 = Mockito.mock(Player.class);
        Collisions.collide(player, player1);
        Mockito.verifyZeroInteractions(player, player1);
    }

}
