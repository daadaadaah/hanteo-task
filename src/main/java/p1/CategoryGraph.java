package p1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CategoryGraph {

    private Map<Integer, CategoryVertex> categories = new HashMap<>();
    private Map<Integer, Set<Integer>> parents = new HashMap<>();
    private Map<Integer, Set<Integer>> children = new HashMap<>();
    private Map<Integer, Board> boards = new HashMap<>();
    private int nextId = 1;

    public int addCategoryVertex(String categoryName) {
        int id = nextId++;
        CategoryVertex vertex = new CategoryVertex(id, categoryName);
        categories.put(id, vertex);
        parents.put(id, new HashSet<>());
        children.put(id, new HashSet<>());
        return id;
    }

    public void addCategoryEdge(CategoryEdge edge) {
        children.computeIfAbsent(edge.parentId, k -> new HashSet<>()).add(edge.childId);
        parents.computeIfAbsent(edge.childId, k -> new HashSet<>()).add(edge.parentId);
    }

    public void addBoard(int categoryId, Board board) {
        boards.put(categoryId, board);
    }

    /**
     * getBoardsById 는 카테고리 식별자로 검색하여 해당 게시판을 모두 가져오는 함수입니다.
     *
     * 예) "익명게시판" // 식별자 검색시 동일한 게시판이지만 여러 그룹(예 : 익명게시판 - BTS, 블랙핑크)에 존재할 수 있으므로, 구별짓기 위해 모든 상/하위 카테고리 값을 보여주도록 하였습니다.
     * [{"no":1,"name":"게시판 1번","categories":[{"id":1,"name":"남자"},{"id":7,"name":"BTS"},{"id":10,"name":"익명게시판"}],"posts":[]},
     * {"no":1,"name":"게시판 1번","categories":[{"id":11,"name":"여자"},{"id":12,"name":"블랙핑크"},{"id":10,"name":"익명게시판"}],"posts":[]}]
     */
    public List<Board> getBoardsById(int categoryId) {
        List<Board> boards = new ArrayList<>();
        List<CategoryVertex> currentPath = new ArrayList<>();
        List<List<CategoryVertex>> ancestorPaths = new ArrayList<>();

        Set<Integer> visited = new HashSet<>();

        getAncestorPaths(categoryId, new ArrayList<>(), ancestorPaths);

        for (List<CategoryVertex> ancestorPath : ancestorPaths) {
            currentPath.addAll(ancestorPath);
            getAllPathsHelper(categoryId, currentPath, boards, visited);
            currentPath.clear();
        }
        return boards;
    }

    /**
     * getBoarsByName 는 카테고리 이름으로 검색하여 해당 게시판을 모두 가져오는 함수입니다.
     *
     * 예) "공지사항" // 이름 검색시 중복이 발생할 수 있으므로(예 : 공지사항), 구별짓기 위해 모든 상/하위 카테고리 값을 보여주도록 하였습니다.
     * [{"no":2,"name":"게시판 2번","categories":[{"id":1,"name":"남자"},{"id":2,"name":"엑소"},{"id":3,"name":"공지사항"}],"posts":[]},
     * {"no":6,"name":"게시판 6번","categories":[{"id":1,"name":"남자"},{"id":7,"name":"BTS"},{"id":8,"name":"공지사항"}],"posts":[]},
     * {"no":8,"name":"게시판 8번","categories":[{"id":11,"name":"여자"},{"id":12,"name":"블랙핑크"},{"id":13,"name":"공지사항"}],"posts":[]}]
     */
    public List<Board> getBoarsByName(String categoryName) {
        List<Board> allPaths = new ArrayList<>();
        for (CategoryVertex vertex : categories.values()) {
            if (vertex.getName().equals(categoryName)) {
                allPaths.addAll(getBoardsById(vertex.getId()));
            }
        }
        return allPaths;
    }

    private void getAncestorPaths(int currentId, List<CategoryVertex> path, List<List<CategoryVertex>> ancestorPaths) {
        path.add(categories.get(currentId));

        if (!parents.containsKey(currentId) || parents.get(currentId).isEmpty()) {
            Collections.reverse(path);
            ancestorPaths.add(path);
        } else {
            for (int parentId : parents.get(currentId)) {
                getAncestorPaths(parentId, new ArrayList<>(path), ancestorPaths);
            }
        }
    }

    private void getAllPathsHelper(int currentId, List<CategoryVertex> currentPath, List<Board> allPaths, Set<Integer> visited) {
        if (visited.contains(currentId)) {
            return;
        }
        visited.add(currentId);

        if (boards.containsKey(currentId)) {
            Board board = new Board(boards.get(currentId).getNo(), boards.get(currentId).getName());
            board.getCategories().addAll(new ArrayList<>(currentPath));
            allPaths.add(board);
        }

        for (int childId : children.getOrDefault(currentId, new HashSet<>())) {
            currentPath.add(categories.get(childId));
            getAllPathsHelper(childId, currentPath, allPaths, visited);
            currentPath.remove(currentPath.size() - 1);
        }

        visited.remove(currentId);
    }
}
