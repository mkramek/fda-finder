import { describe, expect, it, vi } from "vitest";
import { mount } from "@vue/test-utils";
import FDAForm from "../form/FDAForm.vue";
import axios from "axios";
import FDAResponse from "../../helpers/FDAResponse";

const mockResponse: FDAResponse = {
    manufacturer: 'test',
    products: [
        {
            brand_name: 'test brand 1',
            dosage_form: 'tablets',
            marketing_status: 'prescription'
        },
        {
            brand_name: 'test brand 2',
            dosage_form: 'tablets',
            marketing_status: 'prescription'
        },
        {
            brand_name: 'test brand 3',
            dosage_form: 'tablets',
            marketing_status: 'prescription'
        },
        {
            brand_name: 'test brand 4',
            dosage_form: 'tablets',
            marketing_status: 'prescription'
        }
    ]
}

vi.spyOn(axios, 'get').mockResolvedValue(mockResponse);

describe('FDAForm test', () => {
    it("won't submit unless the required field is filled", () => {
        const wrapper = mount(FDAForm);
        wrapper.find('button[type="submit"]').trigger('click');
        expect(axios.get).toBeCalledTimes(0);
    });

    it("will submit after filling the required fields", () => {
        const wrapper = mount(FDAForm);
        wrapper.find('input[required]').setValue('teva');
        wrapper.find('form').trigger('submit.prevent');
        expect(axios.get).toBeCalledTimes(1);
    });
});