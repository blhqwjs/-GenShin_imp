package com.example.genshin_imp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(RobolectricTestRunner.class)
public class AddCharacterActivityTest {

    private AddCharacterActivity addCharacterActivity;

    @Before
    public void setUp() {
        addCharacterActivity = Robolectric.buildActivity(AddCharacterActivity.class).create().get();
    }

    @Test
    public void testSaveButtonClicked_Success() {
        // 模拟用户输入
        addCharacterActivity.nameEditText.setText("角色名");
        // ...（设置其他输入框的内容）

        // 模拟保存按钮点击
        addCharacterActivity.saveButton.performClick();

        // 断言按钮点击后的预期结果
        assertEquals("角色添加成功", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testSaveButtonClicked_Failure() {
        // 模拟无效的用户输入
        addCharacterActivity.nameEditText.setText(""); // 角色名为空

        // 模拟保存按钮点击
        addCharacterActivity.saveButton.performClick();

        // 断言按钮点击后的预期结果
        assertEquals("添加失败，请重试", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testInvalidInput_EmptyFields() {
        // 测试所有字段为空时的情况
        addCharacterActivity.saveButton.performClick();

        // 断言按钮点击后的预期结果
        assertEquals("添加失败，请重试", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testInvalidInput_InvalidNumericValues() {
        // 测试无效的数值输入（例如，攻击力为负数）
        addCharacterActivity.nameEditText.setText("Valid Name");
        addCharacterActivity.attackEditText.setText("-50");

        // 模拟保存按钮点击
        addCharacterActivity.saveButton.performClick();

        // 断言按钮点击后的预期结果
        assertEquals("添加失败，请重试", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testValidInput_NumericValues() {
        // 测试有效的数值输入
        addCharacterActivity.nameEditText.setText("Valid Name");
        addCharacterActivity.attackEditText.setText("100");

        // 模拟保存按钮点击
        addCharacterActivity.saveButton.performClick();

        // 断言按钮点击后的预期结果
        assertEquals("角色添加成功", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testEditTextFields() {
        // 检查编辑文本字段是否正确初始化
        assertNotEquals(null, addCharacterActivity.nameEditText);
        assertNotEquals(null, addCharacterActivity.attributeEditText);
        // ...（检查其他编辑文本字段的初始化）
    }

    // 可以根据需要添加更多测试方法，覆盖不同的情况和边界条件

}
