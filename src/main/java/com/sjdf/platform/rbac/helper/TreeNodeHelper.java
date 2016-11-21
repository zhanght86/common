package com.sjdf.platform.rbac.helper;

import com.sjdf.platform.rbac.bean.Permission;
import com.sjdf.platform.rbac.bean.PermissionNode;
import com.sjdf.platform.rbac.bean.TreeNode;

import java.util.*;

/**
 * User: ketqi
 * Date: 2013-04-15 11:28
 * <p/>
 * 树节点辅助器
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class TreeNodeHelper {

    /** 与convertTreeNodeMapToSortedTreeList一致,不一样的是,此方法返回与原类型一致的数据类型 */
    public static <T extends TreeNode> List<T> convertTreeNodeMapToSortedTreeListReturnT(Map<Long, List<T>> map, Long parentId) {
        List<T> tList = map.get(parentId);
        if (tList == null) {
            return Collections.emptyList();
        }
        for (T t : tList) {
            t.setChildList(convertTreeNodeMapToSortedTreeListReturnT(map, t.getId()));
        }
        return tList;
    }

    /**
     * 根据parentId和parentId与treeNode的map,将其转化成一个具有级联关系的树结构
     * 在这种结构中,以parentId为list中的结点信息,然后对于每一个结点,再将其id作为parentId从map中取出子结点列表,作为此结点的childList
     * 这是一种递归的数据组织方式
     */
    public static <T extends TreeNode> List<TreeNode> convertTreeNodeMapToSortedTreeList(Map<Long, List<T>> map, Long parentId) {
        List<T> tList = map.get(parentId);
        if (tList == null) {
            return Collections.emptyList();
        }
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>(tList);
        for (TreeNode t : treeNodeList) {
            t.setChildList(convertTreeNodeMapToSortedTreeList(map, t.getId()));
        }
        return treeNodeList;
    }

    /** 根据treeNode的parent将同一个parentId的treeNode组合在一起,以parentId为键,treeNode为值 */
    public static <T extends TreeNode> Map<Long, List<T>> makeTreeNodeListToMap(Collection<T> nodeList) {
        Map<Long, List<T>> map = new HashMap<>();
        Long parentId;
        for (T t : nodeList) {
            if (t.getParentNode() == null) {
                parentId = 0L;
            } else {
                parentId = t.getParentNode().getId();
            }
            List<T> list = map.get(parentId);
            if (list == null) {
                list = new ArrayList<>();
                map.put(parentId, list);
            }
            list.add(t);
        }
        return map;
    }

    /**
     * 将一个树结构中,再加上这个节点中的所有上级,完全地返回回来
     * 即将树节点的所有上级节点+原来的节点,一起返回
     */
    public static <T extends TreeNode> Collection<T> addAllParentTreeNode(Collection<T> treeNodeCollection) {
        //先将原来的全部装入
        Set<T> tSet = new HashSet<>(treeNodeCollection);
        //为提高效率,增加用于设置访问的标识,表示已经访问过了
        Set<Long> idTempSet = new HashSet<>();
        for (T t : treeNodeCollection) {
            while (t.getParentNode() != null) {
                t = (T) t.getParentNode();
                Long id = t.getId();
                if (idTempSet.contains(id)) {
                    break;
                }
                tSet.add(t);
                idTempSet.add(id);
            }
        }
        return tSet;
    }

    /** 将权限列表组合成以permissionNodeId为键,permission为值的映射结构,相同permissionNodeId的permission组合在一起 */
    public static Map<Long, List<Permission>> makePermissionListToMap(List<Permission> permissionList) {
        Map<Long, List<Permission>> map = new HashMap<>();
        Long permissionNodeId;
        for (Permission permission : permissionList) {
            if (permission.getPermissionNode() == null) {
                permissionNodeId = 0L;
            } else {
                permissionNodeId = permission.getPermissionNode().getId();
            }
            List<Permission> list = map.get(permissionNodeId);
            if (list == null) {
                list = new ArrayList<>();
                map.put(permissionNodeId, list);
            }
            list.add(permission);
        }
        return map;
    }

    /** 将权限结点Map和权限Map转化成以权限结点为listNode的一个list,每一个listNode的childnode由该结点的子权限结点和权限列表组成 */
    public static List<TreeNode> convertPermissionMapToSortedTreeList(Map<Long, List<PermissionNode>> permissionNodeMap, Map<Long, List<Permission>> permissionMap, Long parentId) {
        List<PermissionNode> permissionNodeList = permissionNodeMap.get(parentId);
        List<Permission> permissionList = permissionMap.get(parentId);
        if (permissionNodeList == null && permissionList == null) {
            return Collections.emptyList();
        }
        List<TreeNode> treeNodeList;
        if (permissionNodeList != null) {
            treeNodeList = new ArrayList<TreeNode>(permissionNodeList);
        } else {
            treeNodeList = new ArrayList<>();
        }
        for (TreeNode treeNode : treeNodeList) {
            treeNode.setChildList(convertPermissionMapToSortedTreeList(permissionNodeMap, permissionMap, treeNode.getId()));
        }
        //再加上权限List
        if (permissionList != null) {
            for (Permission permission : permissionList) {
                TreeNode node = new TreeNode(permission.getId(), permission.getName());
                node.setNameParam("permissionIds");
                //设置checked状态,与permission一致
                node.setChecked(permission.isChecked());
                treeNodeList.add(node);
            }
        }
        return treeNodeList;
    }
}