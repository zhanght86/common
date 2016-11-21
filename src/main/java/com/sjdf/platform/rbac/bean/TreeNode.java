package com.sjdf.platform.rbac.bean;

import com.sjdf.platform.common.annotations.ModifyRecord;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import javax.persistence.*;
import java.util.List;

/**
 * User: ketqi
 * Date: 2013-04-15 10:42
 * <p/>
 * 树结点, 维护着一个树的数据结构
 *
 * @param <T>
 */
@MappedSuperclass
@SuppressWarnings("rawtypes")
public class TreeNode<T extends TreeNode> extends BaseBean {
    private static final long serialVersionUID = -6850366266805830941L;
    protected static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(TreeNode.class);

    protected static final String PATH_SEPARATOR = "/";
    protected static final String ROOT_NAME = "世纪东方技术平台";
    protected static final String ROOT_NAME_PARAM = "root";

    /** 名称 */
    protected String name;

    /** 等级 */
    protected int level;

    /** 上级 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    protected T parent;

    /** 路径 */
    protected String path;

    /** 下级集 */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    protected List<T> childList;

    /** 是否被选中 */
    @Transient
    private transient boolean checked;

    /** 客户端name参数,默认为ids */
    @Transient
    private transient String nameParam = "ids";

    public TreeNode() {
    }

    public TreeNode(long id, String name) {
        setId(id);
        this.name = name;
    }

    /**
     * 设置一个父级,并设置相应的level和path值
     *
     * @param parent 上级
     */
    public void parent(T parent) {
        if (parent == null) {
            setParent(null);
            level = 1;
            path = PATH_SEPARATOR;
        } else {
            setParent(parent);
            if (level == 0) {
                level = parent.getLevel() + 1;
            }
            if (path == null) {
                path = parent.getPath() + parent.getId() + PATH_SEPARATOR;
            }
        }
    }

    /** 得到自己的路径 */
    @Transient
    public String getSelfPath() {
        return (getPath() == null) ? PATH_SEPARATOR : (getPath() + getId() + PATH_SEPARATOR);
    }

    /** 是否是指定结点的上级 */
    public boolean isParent(T node) {
        // 直接用path进行判断
        return getSelfPath().equals(node.getPath());
    }

    /** 是否是指定结点的祖先 */
    public boolean isAncestor(T node) {
        return node.getPath().startsWith(getSelfPath());
    }

    /**
     * @param clazz 类型
     * @param <T>
     * @return 创建根节点
     */
    public static <T extends TreeNode> T createRootNode(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException ignore) {
            LOGGER.error(ignore.getMessage(), ignore);
        }
        if (t == null) {
            throw new IllegalArgumentException("实际化树结点失败");
        }
        t.setName(ROOT_NAME);
        t.setNameParam(ROOT_NAME_PARAM);
        return t;
    }

    /** 初始化数据,避免failed to lazily initialize a collection */
    public void hibernateInitialize() {
        if (getChildList() != null && !getChildList().isEmpty()) {
            for (T t : childList) {
                if (t.getChildList() != null && !t.getChildList().isEmpty()) {
                    t.hibernateInitialize();
                }
            }
        }
    }

    /**
     * parent结点,这里不能用getParent()是由于在某些条件下,通过javaBean取子类的parent
     * type会返回treeNode,而不是子类的类型 而造成在进行set时,会出现classcastException
     */
    public T getParentNode() {
        return parent;
    }

    @ModifyRecord(name = "名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ModifyRecord(name = "等级")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    @ModifyRecord(name = "路径")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<T> getChildList() {
        return childList;
    }

    public void setChildList(List<T> childList) {
        this.childList = childList;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getNameParam() {
        return nameParam;
    }

    public void setNameParam(String nameParam) {
        this.nameParam = nameParam;
    }
}
