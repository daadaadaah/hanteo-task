package p1;


public class CategoryEdge {
    int parentId;
    int childId;

    public CategoryEdge(int parentId, int childId) {
        this.parentId = parentId;
        this.childId = childId;
    }
}