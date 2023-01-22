import { describe, expect, it } from "vitest";
import { mount } from "@vue/test-utils";
import App from "../../App.vue";
import { store, key } from "../../store";

describe('Vuex test', () => {
    it('sets theme to dark', () => {
        const wrapper = mount(App, {
            global: {
                plugins: [[store, key]]
            }
        });
        wrapper.find('button[class="flex items-center justify-center"]').trigger('click');
        expect(wrapper.html()).toContain('dark');
    });
});