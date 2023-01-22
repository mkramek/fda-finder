import { describe, it, expect } from "vitest";
import { mount } from '@vue/test-utils';
import TotalCount from "../TotalCount.vue";

describe('TotalCount test', () => {
    it('sets a value from props', () => {
        const wrapper = mount(TotalCount, {
            props: {
                count: 10
            }
        });

        expect(wrapper.text()).toMatch('Total products count: 10');
    });
});