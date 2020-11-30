package com.zhuo.newsmanag.jsqxgl;

import javax.swing.table.AbstractTableModel;
/*
 * 权限表模型
 */
@SuppressWarnings({"serial"})
public class QXTableModel extends AbstractTableModel
{
    //指定单元格的值是否改变
    boolean isCellChanged = false;

    //最近编辑过的行
    int lastEditRow ;
    JSQXGLPanel jsqxglpn;

    public QXTableModel(JSQXGLPanel jsqxglpn)
    {
        this.jsqxglpn=jsqxglpn;
    }
    //获得列数
    @Override
    public int getColumnCount() {
        return 2;
    }

    //获得行数
    @Override
    public int getRowCount() {
        return jsqxglpn.tableDataQX.size();
    }

    //获得某单元格的值
    @Override
    public Object getValueAt(int r, int c) {
        return jsqxglpn.tableDataQX.get(r)[c];
    }

    //获得每一列的类型
    @Override
    public Class<?> getColumnClass(int arg0) {
        return jsqxglpn.typeArrayQX[arg0];
    }

    //获得每一列的列头
    @Override
    public String getColumnName(int arg0) {
        return jsqxglpn.headQX[arg0];
    }

    //设置是否可编辑
    @Override
    public boolean isCellEditable(int r, int c) {
        return false;
    }

    //设置某一单元格的值   表格内的值变得时候调用
    @Override
    public void setValueAt(Object value, int r, int c)
    {

    }
}
