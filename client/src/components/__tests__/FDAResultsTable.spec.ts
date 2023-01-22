import { describe, expect, it } from "vitest";
import { mount } from "@vue/test-utils";
import FDAResponse from "../../helpers/FDAResponse";
import FDAResultsTable from "../FDAResultsTable.vue";

const mockData: FDAResponse = {
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

describe('Results table test', () => {
    it('parses properly received data', () => {
        const wrapper = mount(FDAResultsTable, {
            props: {
                results: mockData
            }
        });
        expect(wrapper.find('td').text()).toContain(mockData.manufacturer);
        expect(wrapper.find('td:nth-of-type(2)').text()).toContain(mockData.products[0].brand_name);
    });
});