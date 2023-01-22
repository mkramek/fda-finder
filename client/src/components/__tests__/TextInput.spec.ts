import { describe, it, expect } from "vitest";
import { mount, shallowMount } from '@vue/test-utils';
import TextInput from "../form/TextInput.vue";

describe('TextInput test', () => {
    it('sets a label from props', () => {
        const wrapper = shallowMount(TextInput, {
            props: {
                label: 'test'
            }
        });

        expect(wrapper.get('label').text()).toMatch('test');
    });

    it('sets the value', () => {
        const wrapper = mount(TextInput);
        const input = wrapper.find('input');
        input.setValue('input value');
        expect(input.element.value).toMatch('input value');
    });
});