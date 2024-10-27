package p1;

import java.util.List;

public class Problem1 {

    public static void main(String[] args) {
        CategoryGraph graph = new CategoryGraph();

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

        graph.addBoard(anonymousId, new Board(1, "게시판 1번"));
        graph.addBoard(exoNoticeId, new Board(2, "게시판 2번"));
        graph.addBoard(chenId, new Board(3, "게시판 3번"));
        graph.addBoard(baekhyunId, new Board(4, "게시판 4번"));
        graph.addBoard(xiuminId, new Board(5, "게시판 5번"));
        graph.addBoard(btsNoticeId, new Board(6, "게시판 6번"));
        graph.addBoard(VId, new Board(7, "게시판 7번"));
        graph.addBoard(blackpinkNoticeId, new Board(8, "게시판 8번"));
        graph.addBoard(roseId, new Board(9, "게시판 9번"));


        System.out.println("========================식별자로 검색===================================");


        System.out.println("-------------------------남자------------------------------");

        // 모든 경로 출력
        List<Board> allPaths = graph.getBoardsById(manId);
        for (Board path : allPaths) {
            System.out.println(path);
        }
        System.out.println("---------------------------공지사항(exo)---------------------------");

        List<Board> allPaths2 = graph.getBoardsById(exoNoticeId);
        for (Board path : allPaths2) {
            System.out.println(path);
        }
        System.out.println("---------------------------BTS----------------------------");

        List<Board> allPaths3 = graph.getBoardsById(btsId);
        for (Board path : allPaths3) {
            System.out.println(path);
        }
        System.out.println("-----------------------------익명--------------------------");

        List<Board> allPaths4 = graph.getBoardsById(anonymousId);
        for (Board path : allPaths4) {
            System.out.println(path);
        }

        System.out.println("-----------------------------로제--------------------------");

        List<Board> allPaths5 = graph.getBoardsById(roseId);
        for (Board path : allPaths5) {
            System.out.println(path);
        }

        // 이름만 검색하는 경우
        System.out.println("========================이름으로 검색===================================");

        System.out.println("-------------------------남자------------------------------");

        // 모든 경로 출력
        List<Board> allManBoards = graph.getBoarsByName("남자");
        for (Board path : allManBoards) {
            System.out.println(path);
        }
        System.out.println("---------------------------공지사항---------------------------");

        List<Board> allNoticeBoards = graph.getBoarsByName("공지사항");
        for (Board path : allNoticeBoards) {
            System.out.println(path);
        }
        System.out.println("---------------------------BTS----------------------------");

        List<Board> allBTSBoards = graph.getBoarsByName("BTS");
        for (Board path : allBTSBoards) {
            System.out.println(path);
        }
        System.out.println("-----------------------------익명게시판--------------------------");

        List<Board> allAnonymousBoards = graph.getBoarsByName("익명게시판");
        for (Board path : allAnonymousBoards) {
            System.out.println(path);
        }

        System.out.println("-----------------------------로제--------------------------");

        List<Board> allRoseBoards = graph.getBoarsByName("로제");
        for (Board path : allRoseBoards) {
            System.out.println(path);
        }
    }
}
