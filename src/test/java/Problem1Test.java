import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import p1.Board;
import p1.CategoryEdge;
import p1.CategoryGraph;

public class Problem1Test {
    private ObjectMapper objectMapper = new ObjectMapper();
    private CategoryGraph graph;

    @BeforeEach
    void init() {
        graph = new CategoryGraph();

        // 카테고리 노드 추가 (이름)
        int manId = graph.addCategoryVertex("남자"); // 1
        int exoId = graph.addCategoryVertex("엑소"); // 2
        int exoNoticeId = graph.addCategoryVertex("공지사항"); // 3
        int chenId = graph.addCategoryVertex("첸"); // 4
        int baekhyunId = graph.addCategoryVertex("백현"); // 5
        int xiuminId = graph.addCategoryVertex("시우민"); // 6
        int btsId = graph.addCategoryVertex("BTS"); // 7
        int btsNoticeId = graph.addCategoryVertex("공지사항"); // 8
        int VId = graph.addCategoryVertex("뷔"); // 9
        int anonymousId = graph.addCategoryVertex("익명게시판"); // 10
        int womanId = graph.addCategoryVertex("여자"); // 11
        int blackpinkId = graph.addCategoryVertex("블랙핑크"); // 12
        int blackpinkNoticeId = graph.addCategoryVertex("공지사항"); // 13
        int roseId = graph.addCategoryVertex("로제"); // 14

        // 부모-자식 관계 추가 (parent_idx, child_id로 저장)
        graph.addCategoryEdge(new CategoryEdge(manId, exoId));  // 남자 -> 엑소
        graph.addCategoryEdge(new CategoryEdge(exoId, exoNoticeId));  // 엑소 -> 공지사항
        graph.addCategoryEdge(new CategoryEdge(exoId, chenId));  // 엑소 -> 첸
        graph.addCategoryEdge(new CategoryEdge(exoId, baekhyunId));  // 엑소 -> 백현
        graph.addCategoryEdge(new CategoryEdge(exoId, xiuminId));  // 엑소 -> 시우민
        graph.addCategoryEdge(new CategoryEdge(manId, btsId));  // 남자 -> BTS
        graph.addCategoryEdge(new CategoryEdge(btsId, btsNoticeId));  // BTS -> 공지사항
        graph.addCategoryEdge(new CategoryEdge(btsId, anonymousId));  // BTS -> 익명게시판
        graph.addCategoryEdge(new CategoryEdge(btsId, VId)); // BTS -> 뷔
        graph.addCategoryEdge(new CategoryEdge(womanId, blackpinkId)); // 여자 -> 블랙핑크
        graph.addCategoryEdge(new CategoryEdge(blackpinkId, blackpinkNoticeId)); // 블랙핑크 -> 공지사항
        graph.addCategoryEdge(new CategoryEdge(blackpinkId, anonymousId));  // 블랙핑크 -> 익명게시판 (다중 부모 관계)
        graph.addCategoryEdge(new CategoryEdge(blackpinkId, roseId)); // 블랙핑크 -> 로제

        // 게시판 추가

        graph.addBoard(exoNoticeId, new Board(1, "게시판 1번"));
        graph.addBoard(chenId, new Board(2, "게시판 2번"));
        graph.addBoard(baekhyunId, new Board(3, "게시판 3번"));
        graph.addBoard(xiuminId, new Board(4, "게시판 4번"));
        graph.addBoard(btsNoticeId, new Board(5, "게시판 5번"));
        graph.addBoard(anonymousId, new Board(6, "게시판 6번"));
        graph.addBoard(VId, new Board(7, "게시판 7번"));
        graph.addBoard(blackpinkNoticeId, new Board(8, "게시판 8번"));
        graph.addBoard(roseId, new Board(9, "게시판 9번"));
    }

//    @Test
//    void 남자_식별자로_검색하면_남자_그룹의_모든_게시판이_보인다() throws Exception {
//        List<Board> boards = graph.getBoardsById(1);
//
//        String jsonString = objectMapper.writeValueAsString(boards);
//
//
//        assertEquals(true, jsonString.contains("엑소"));
//        assertEquals(true, jsonString.contains("첸"));
//        assertEquals(true, jsonString.contains("백현"));
//        assertEquals(true, jsonString.contains("시우민"));
//
//        assertEquals(true, jsonString.contains("BTS"));
//        assertEquals(true, jsonString.contains("뷔"));
//    }
//
//    @Test
//    void 엑소_공지사항_식별자로_검색하면_엑소_공지사항_게시판만_보인다() throws Exception {
//        List<Board> boards = graph.getBoardsById(3);
//
//        String jsonString = objectMapper.writeValueAsString(boards);
//
//        assertEquals(true, jsonString.contains("엑소"));
//        assertEquals(true, jsonString.contains("공지사항"));
//
//        // 다른 그룹 공지사항은 보이지 않는다.
//        assertEquals(false, jsonString.contains("BTS"));
//        assertEquals(false, jsonString.contains("블랙핑크"));
//    }
//
//    @Test
//    void BTS_식별자로_검색하면_BTS_모든_게시판이_보인다() throws Exception {
//        List<Board> boards = graph.getBoardsById(7);
//
//        String jsonString = objectMapper.writeValueAsString(boards);
//
//        assertEquals(true, jsonString.contains("공지사항"));
//        assertEquals(true, jsonString.contains("뷔"));
//        assertEquals(true, jsonString.contains("익명게시판"));
//
//        // 다른 그룹 멤버 게시판은 보이지 않는다.
//        assertEquals(false, jsonString.contains("백현"));
//    }
//
//    @Test
//    void 익명게시판_식별자로_검색하면_익명게시판을_가진_모든_그룹_게시판이_보인다() throws Exception {
//        List<Board> boards = graph.getBoardsById(10);
//
//        String jsonString = objectMapper.writeValueAsString(boards);
//
//        assertEquals(true, jsonString.contains("익명게시판"));
//        assertEquals(true, jsonString.contains("BTS"));
//        assertEquals(true, jsonString.contains("블랙핑크"));
//
//
//        // 익명게시판이 없는 그룹의 게시판은 보이지 않는다.
//        assertEquals(false, jsonString.contains("엑소"));
//    }
//
//    @Test
//    void 로제_식별자로_검색하면_로제_게시판만_보인다() throws Exception {
//        List<Board> boards = graph.getBoardsById(14);
//
//        String jsonString = objectMapper.writeValueAsString(boards);
//
//
//        assertEquals(true, jsonString.contains("블랙핑크"));
//        assertEquals(true, jsonString.contains("로제"));
//
//        // 블랙핑크의 다른 게시판은 보이지 않는다.
//        assertEquals(false, jsonString.contains("공지사항"));
//
//        // 다른 그룹의 멤버 게시판은 보이지 않는다.
//        assertEquals(false, jsonString.contains("첸"));
//    }

    @Test
    void 남자_이름으로_검색하면_남자_그룹의_모든_게시판이_보인다() throws Exception {
        List<Board> boards = graph.getBoarsByName("남자");

        String jsonString = objectMapper.writeValueAsString(boards);


        assertEquals(true, jsonString.contains("엑소"));
        assertEquals(true, jsonString.contains("첸"));
        assertEquals(true, jsonString.contains("백현"));
        assertEquals(true, jsonString.contains("시우민"));

        assertEquals(true, jsonString.contains("BTS"));
        assertEquals(true, jsonString.contains("뷔"));
    }

    @Test
    void 공지사항_이름으로_검색하면_공지사항이_있는_모든_그룹의_게시판이_보인다() throws Exception {
        List<Board> boards = graph.getBoarsByName("공지사항");

        String jsonString = objectMapper.writeValueAsString(boards);

        assertEquals(true, jsonString.contains("엑소"));
        assertEquals(true, jsonString.contains("BTS"));
        assertEquals(true, jsonString.contains("블랙핑크"));
    }

    @Test
    void BTS_이름으로_검색하면_BTS_모든_게시판이_보인다() throws Exception {
        List<Board> boards = graph.getBoarsByName("BTS");

        String jsonString = objectMapper.writeValueAsString(boards);

        assertEquals(true, jsonString.contains("공지사항"));
        assertEquals(true, jsonString.contains("뷔"));
        assertEquals(true, jsonString.contains("익명게시판"));

        // 다른 그룹 멤버 게시판은 보이지 않는다.
        assertEquals(false, jsonString.contains("엑소"));
    }

    @Test
    void 익명게시판_이름으로_검색하면_익명게시판을_가진_모든_그룹_게시판이_보인다() throws Exception {
        List<Board> boards = graph.getBoarsByName("익명게시판");

        String jsonString = objectMapper.writeValueAsString(boards);

        assertEquals(true, jsonString.contains("익명게시판"));
        assertEquals(true, jsonString.contains("BTS"));
        assertEquals(true, jsonString.contains("블랙핑크"));


        // 익명게시판이 없는 그룹의 게시판은 보이지 않는다.
        assertEquals(false, jsonString.contains("엑소"));
    }

    @Test
    void 로제_이름로_검색하면_로제_게시판만_보인다() throws Exception {
        List<Board> boards = graph.getBoarsByName("로제");

        String jsonString = objectMapper.writeValueAsString(boards);


        assertEquals(true, jsonString.contains("블랙핑크"));
        assertEquals(true, jsonString.contains("로제"));

        // 블랙핑크의 다른 게시판은 보이지 않는다.
        assertEquals(false, jsonString.contains("공지사항"));

        // 다른 그룹의 멤버 게시판은 보이지 않는다.
        assertEquals(false, jsonString.contains("첸"));
    }
}